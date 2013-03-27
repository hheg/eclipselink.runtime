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
package org.eclipse.persistence.testing.tests.eis.cobol;

import java.io.*;
import java.util.*;
import org.eclipse.persistence.internal.eis.cobol.*;
import org.eclipse.persistence.testing.framework.*;

public class MultipleRecordParseTest extends CobolTest {
    Vector records;

    public String description() {
        return "This test will parse a stream containing multiple records and extraneous information.";
    }

    protected void test() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(CobolTestModel.getMultipleRecordString().getBytes());
        CopyBookParser parser = new CopyBookParser();
        try {
            records = parser.parse(inputStream);
        } catch (Exception exception) {
            TestErrorException testException = new TestErrorException(exception.getMessage());
            setTestException(testException);
        }
    }

    protected void verify() {
        Enumeration recordsEnum = records.elements();
        Enumeration recordsEnum2 = CobolTestModel.getMultipleRecords().elements();
        if (records.size() > 2) {
            TestErrorException exception = new TestErrorException("returned too many records.");
            setTestException(exception);
        }
        while (recordsEnum.hasMoreElements()) {
            if (!CobolTestModel.compareCompositeObjects((RecordMetaData)recordsEnum.nextElement(), (RecordMetaData)recordsEnum2.nextElement())) {
                TestErrorException exception = new TestErrorException("The records do not match.");
                setTestException(exception);
            }
        }
    }
}
