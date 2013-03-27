/*******************************************************************************
 * Copyright (c) 1998, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.mappings.keybased.singletarget.singlekey.attributekey;

import java.util.ArrayList;

import org.eclipse.persistence.testing.oxm.mappings.keybased.KeyBasedMappingTestCases;
import org.eclipse.persistence.testing.oxm.mappings.keybased.Address;
import org.eclipse.persistence.testing.oxm.mappings.keybased.Root;
import org.eclipse.persistence.testing.oxm.mappings.keybased.singletarget.Employee;

public class SingleAttributeKeyTestCases extends KeyBasedMappingTestCases {
	private final static String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/keybased/singletarget/singlekey/attributekey/instance.xml";

	public SingleAttributeKeyTestCases(String name) throws Exception {
		super(name);
		setControlDocument(XML_RESOURCE);
		setProject(new SingleAttributeKeyProject());
	}

	protected Object getControlObject() {
		Address address = new Address();
		address.id = CONTROL_ADD_ID_1;
		address.street = CONTROL_ADD_STREET_1;
		address.city = CONTROL_ADD_CITY_1;
		address.country = CONTROL_ADD_COUNTRY_1;
		address.zip = CONTROL_ADD_ZIP_1;

		Employee employee = new Employee();
		employee.id = CONTROL_ID;
		employee.name = CONTROL_NAME;
		employee.address = address;

		Root root = new Root();
		root.employee = employee;
		return root;
	}

	public Object getWriteControlObject() {
		ArrayList rootAddresses = new ArrayList();

		Address address = new Address();
		address.id = CONTROL_ADD_ID_1;
		address.street = CONTROL_ADD_STREET_1;
		address.city = CONTROL_ADD_CITY_1;
		address.country = CONTROL_ADD_COUNTRY_1;
		address.zip = CONTROL_ADD_ZIP_1;
		rootAddresses.add(address);

		Employee employee = new Employee();
		employee.id = CONTROL_ID;
		employee.name = CONTROL_NAME;
		employee.address = address;

		address = new Address();
		address.id = CONTROL_ADD_ID_2;
		address.street = CONTROL_ADD_STREET_2;
		address.city = CONTROL_ADD_CITY_2;
		address.country = CONTROL_ADD_COUNTRY_2;
		address.zip = CONTROL_ADD_ZIP_2;
		rootAddresses.add(address);

		address = new Address();
		address.id = CONTROL_ADD_ID_3;
		address.street = CONTROL_ADD_STREET_3;
		address.city = CONTROL_ADD_CITY_3;
		address.country = CONTROL_ADD_COUNTRY_3;
		address.zip = CONTROL_ADD_ZIP_3;
		rootAddresses.add(address);

		address = new Address();
		address.id = CONTROL_ADD_ID_4;
		address.street = CONTROL_ADD_STREET_4;
		address.city = CONTROL_ADD_CITY_4;
		address.country = CONTROL_ADD_COUNTRY_4;
		address.zip = CONTROL_ADD_ZIP_4;
		rootAddresses.add(address);

		Root root = new Root();
		root.employee = employee;
		root.addresses = rootAddresses;
		return root;
	}
}
