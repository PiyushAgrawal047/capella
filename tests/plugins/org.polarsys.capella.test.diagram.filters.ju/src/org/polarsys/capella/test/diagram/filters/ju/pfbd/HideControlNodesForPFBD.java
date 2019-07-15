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
package org.polarsys.capella.test.diagram.filters.ju.pfbd;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideControlNodesForPFBD extends FiltersForPFBD {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_PFBD_HIDE_CONTROL_NODES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(CONTROL_NODE_1_ID, CONTROL_NODE_2_ID, CONTROL_NODE_3_ID);
  }

}
