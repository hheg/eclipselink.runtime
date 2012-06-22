/*******************************************************************************
 * Copyright (c) 1998, 2012 Oracle and/or its affiliates. All rights reserved.
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
package org.eclipse.persistence.testing.tests.queries.inmemory;

import java.util.Vector;

import org.eclipse.persistence.queries.*;
import org.eclipse.persistence.expressions.*;
import org.eclipse.persistence.sessions.*;

import org.eclipse.persistence.testing.framework.*;
import org.eclipse.persistence.testing.models.employee.domain.*;

/**
 * Test selecting using an object's primary key to ensure that it does not go to the databaase.
 */
public class UnitOfWorkConformTest extends TestCase {
    protected int expected;
    protected UnitOfWork uow;
    protected Vector result;
    protected ReadAllQuery query;

    public UnitOfWorkConformTest(ReadAllQuery query, int size) {
        this.expected = size;
        this.query = query;
        setDescription("Test that the query is done on the unit of work changes.");
    }

    public void reset() {
        uow.release();
    }

    protected void setup() {
        uow = getSession().acquireUnitOfWork();
        uow.readAllObjects(Employee.class);
        Employee newEmployee = new org.eclipse.persistence.testing.models.employee.domain.Employee();
        newEmployee.setFirstName("Bob");
        uow.registerObject(newEmployee);
        newEmployee = new org.eclipse.persistence.testing.models.employee.domain.Employee();
        newEmployee.setFirstName("newBobby");
        uow.registerNewObject(newEmployee);
        uow.deleteObject(uow.readObject(Employee.class, new ExpressionBuilder().get("firstName").equal("Sarah")));

        org.eclipse.persistence.tools.schemaframework.PopulationManager manager = org.eclipse.persistence.tools.schemaframework.PopulationManager.getDefaultManager();
        Employee example = (Employee)manager.getObject(Employee.class, "0001");
        Employee clone = (Employee)uow.readObject(example);
        clone.setLastName("Bobo");
    }

    public void test() {
        this.result = (Vector)this.uow.executeQuery(this.query);
    }

    protected void verify() {
        if (this.result.size() != this.expected) {
            throw new TestErrorException("Expecting: " + this.expected + " retrieved: " + this.result.size());
        }
    }
}
