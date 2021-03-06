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
package org.polarsys.capella.common.helpers.export;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support for this package
 */
public class ExportMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.helpers.export.messages"; //$NON-NLS-1$

  public static String errNullExporterProvider;
  
  public static String csvDesc;
  public static String txtDesc;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, ExportMessages.class);
  }

  private ExportMessages() {
    // Do nothing.
  }
}
