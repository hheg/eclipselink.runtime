/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Mike Norman - May 05 2010
 *       fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=307897
 ******************************************************************************/

package org.eclipse.persistence.tools.dbws;

//EclipseLink imports
import static org.eclipse.persistence.tools.dbws.DBWSPackager.ArchiveUse.noArchive;


/**
 * <p>
 * <b>PUBLIC:</b> EclipsePackager extends {@link IDEPackager}. It is responsible for generating<br>
 * the source code of the DBWS Provider (instead of a <tt>.class</tt> file) and packaging in a<br>
 * Eclipse-friendly directory structure all the other DBWS files produced by its parent:
 * <pre>
 * \--- JDev <b>Projectnnn</b> root directory
 *    |   dbws-builder.xml
 *    |
 *    +---<b>WebContent</b>
 *    |   \---WEB-INF
 *    |       |   <b>web.xml</b>
 *    |       |
 *    |       \---wsdl
 *    |               <b><i>swaref.xsd</i></b>
 *    |               <b>eclipselink-dbws-schema.xsd</b>
 *    |               <b>eclipselink-dbws.wsdl</b>
 *    |
 *    \---<b>src</b>
 *        |   <b>eclipselink-dbws-or.xml</b>
 *        |   <b>eclipselink-dbws-ox.xml</b>
 *        |   <b>eclipselink-dbws-sessions.xml</b>
 *        |   <b>eclipselink-dbws.xml</b>
 *        |
 *        \---_dbws
 *                <b>DBWSProvider.java</b>  -- generated by this Packager
 * </pre>
 *
 * @author Mike Norman - michael.norman@oracle.com
 * @since EclipseLink 2.1
 */
public class EclipsePackager extends IDEPackager {

    public static final String ECLIPSE_PUBLIC_HTML_DIR = "WebContent";

    public EclipsePackager() {
        super(null, "eclipse", noArchive);
        srcDirname = SRC_DIR;
        publicHTMLDirname = ECLIPSE_PUBLIC_HTML_DIR;
    }
}
