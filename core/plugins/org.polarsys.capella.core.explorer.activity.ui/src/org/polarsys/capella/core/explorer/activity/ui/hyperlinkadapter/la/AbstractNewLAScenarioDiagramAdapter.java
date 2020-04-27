/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractNewScenarioDiagramAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelCreationHelper;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

public abstract class AbstractNewLAScenarioDiagramAdapter extends AbstractNewScenarioDiagramAdapter {
  @Override
  protected ModelElement getModelElement(EObject rootSemanticModel) {
    LogicalArchitecture logicalArchitecture = ModelQueryHelper.getLogicalArchitecture((Project) rootSemanticModel);
    return ModelCreationHelper.selectCapability((Project) rootSemanticModel, logicalArchitecture);
  }
}
