/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.es2is.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_InteractionElement;
import org.polarsys.capella.core.projection.scenario.es2is.ES2ISExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 *
 */
public class Rule_Execution extends Rule_InteractionElement {

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    Execution execution = (Execution) element_p;
    InteractionFragment endSource = execution.getStart();
    InteractionFragment endTarget = execution.getFinish();

    if (endSource instanceof AbstractEnd && endTarget instanceof AbstractEnd) {
      return isOrWillBeTransformed(endSource, transfo_p) && isOrWillBeTransformed(endTarget, transfo_p);
    }
    return true;
  }

  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    return ProjectionMessages.EndNotTransitioned;
  }

  /**
   * @param eclass_p
   */
  public Rule_Execution() {
    super(InteractionPackage.Literals.EXECUTION, InteractionPackage.Literals.EXECUTION);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) throws TransfoException {
    TigerRelationshipHelper.attachIemeWithIeme(element_p, getTargetType(), InteractionPackage.Literals.INTERACTION_FRAGMENT,
        InteractionPackage.Literals.TIME_LAPSE__START, transfo_p);
    TigerRelationshipHelper.attachIemeWithIeme(element_p, getTargetType(), InteractionPackage.Literals.INTERACTION_FRAGMENT,
        InteractionPackage.Literals.TIME_LAPSE__FINISH, transfo_p);
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES,
        transfo_p);
  }

  @Override
  @SuppressWarnings("unused")
  public Object transformElement(EObject element_p, ITransfo transfo_p) {

    List<ExchangeItem> eis = ES2ISExt.getExchangeItems((Execution) element_p);
    if (eis.size() <= 1)
      return super.transformElement(element_p, transfo_p);

    List<Execution> result = new ArrayList<Execution>(eis.size());
    for (ExchangeItem exchangeItem : eis) {
      Execution end = (Execution) super.transformElement(element_p, transfo_p);
      result.add(end);
    }
    return result;
  }

}
