/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.lc2pc.breakdownstrategy.rules;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.projection.common.rules.cs.Rule_Component;
import org.polarsys.capella.core.projection.interfaces.ctx2pa.TransformInterfacesCtx2Pa;
import org.polarsys.capella.core.projection.interfaces.la2pa.TransformInterfacesLa2Pa;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.statemachine.TransformStateMachine;

/**
 */
public class Rule_LogicalComponent extends Rule_Component {

  public Rule_LogicalComponent() {
    super(LaPackage.Literals.LOGICAL_COMPONENT, PaPackage.Literals.PHYSICAL_COMPONENT, PaPackage.Literals.LOGICAL_COMPONENT_REALIZATION);
  }

  public Rule_LogicalComponent(EClass source, EClass target) {
    super(source, target, PaPackage.Literals.LOGICAL_COMPONENT_REALIZATION);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return !(element_p.eContainer() instanceof BlockArchitecture);
  }

  @Override
  protected void runSubTransition(EObject element_p, ITransfo transfo_p) {

    EObject transfoSource = (EObject) getTransfo().get(TransfoEngine.TRANSFO_SOURCE);

    //Perform Interface(s) projection manipulated (used/implement) by the current LC
    //(Only in according with user preference)
    if (element_p instanceof InterfacePkg) {
      InterfacePkg sourceElement = (InterfacePkg) element_p;
      TransformInterfacesLa2Pa transfItfLa2pa = new TransformInterfacesLa2Pa();
      transfItfLa2pa.setContext(sourceElement);
      transfItfLa2pa.execute();
      alreadyTransitioned.addAll(InterfacePkgExt.getAllInterfaces(sourceElement));

    } else if (element_p instanceof Interface) {
      Interface sourceElement = (Interface) element_p;
      alreadyTransitioned.add(sourceElement);

      if (EcoreUtil2.isContainedBy(sourceElement, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
        TransformInterfacesCtx2Pa transfItfCtx2pa = new TransformInterfacesCtx2Pa();
        transfItfCtx2pa.setContext(sourceElement);
        transfItfCtx2pa.execute();

      } else if (EcoreUtil2.isContainedBy(sourceElement, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
        TransformInterfacesLa2Pa transfItfLa2Pa = new TransformInterfacesLa2Pa();
        transfItfLa2Pa.setContext(sourceElement);
        transfItfLa2Pa.execute();
      }
    }

    if (EcoreUtil2.isOrIsContainedBy(element_p, transfoSource)) {

      if (element_p instanceof StateMachine) {
        StateMachine sourceElement = (StateMachine) element_p;
        TransformStateMachine transfSM = new TransformStateMachine();
        transfSM.setContext(sourceElement);
        transfSM.execute();

      } else {
        super.runSubTransition(element_p, transfo_p);
      }

    } else {
      super.runSubTransition(element_p, transfo_p);
    }

  }
}