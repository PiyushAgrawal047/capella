/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.MultiplicityElement;

public class GetCurrent_Parameter_MinValue extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> currentElements = getCurrentElements(capellaElement, false);
    return (List) currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element instanceof MultiplicityElement) {
      MultiplicityElement mElement = (MultiplicityElement) element;
      if (mElement.getOwnedMinValue() != null) {
        currentElements.add(mElement.getOwnedMinValue());
      }
    }
    return currentElements;
  }

}