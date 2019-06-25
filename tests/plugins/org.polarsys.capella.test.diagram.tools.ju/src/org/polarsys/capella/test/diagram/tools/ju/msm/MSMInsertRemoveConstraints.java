/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.msm;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class MSMInsertRemoveConstraints extends AbstractDiagramTestCase {

  private final String diagramName = "SA Mode State Machine Test Diagram";

  @Override
  protected String getRequiredTestModel() {
    return "DiagramToolsModel";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MSMDiagram diagram = MSMDiagram.openDiagram(context, this.diagramName, Type.SA);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.createConstraint(GenericModel.CONSTRAINT_2);

    diagram.removeConstraint(GenericModel.CONSTRAINT_1, diagram.getDiagramId());
    diagram.removeConstraint(GenericModel.CONSTRAINT_2, diagram.getDiagramId());

    diagram.insertConstraint(GenericModel.CONSTRAINT_1, diagram.getDiagramId());
    diagram.insertConstraint(GenericModel.CONSTRAINT_2, diagram.getDiagramId());
  }
}