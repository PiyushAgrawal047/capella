/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return Component Exchanges and Delegation of current Component Port
 *
 */
public abstract class AbstractComponentPortComponentExchanges implements IQuery {

	/**
	 * 
	 */
	public AbstractComponentPortComponentExchanges() {
    // do nothing
	}

	/**
	 * 
	 * source.owner
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof ComponentPort) {
		  ComponentPort port = (ComponentPort) object;
			List<AbstractInformationFlow> flows = getInformationFlows(port);
			for (AbstractInformationFlow abstractInformationFlow : flows) {
        if (abstractInformationFlow instanceof ComponentExchange) {
            if (isValidComponentExchange((ComponentExchange) abstractInformationFlow)) {
              result.add(abstractInformationFlow);
          }    
        }
      }
			
		}
		
    return result;
	}
	
	public abstract boolean isValidComponentExchange(ComponentExchange exchange);
	
	public abstract List<AbstractInformationFlow> getInformationFlows(ComponentPort port);
}
