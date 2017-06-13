package org.eclipse.persistence.testing.jaxb;

import java.lang.reflect.Constructor;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.persistence.exceptions.ConversionException;
import org.eclipse.persistence.internal.databaseaccess.Platform;
import org.eclipse.persistence.internal.helper.ConversionManager;
import org.eclipse.persistence.internal.jaxb.XMLJavaTypeConverter;
import org.eclipse.persistence.internal.security.PrivilegedAccessHelper;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.sessions.Login;
import org.eclipse.persistence.sessions.Session;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class XMLJavaTypeConverterTestCases {

    /*
     * The XmlAdapter#marshal and XmlAdapter#unmarshal APIs states that its
     * legal to throw an Exception so the XMLJavaTypeConverter should be able to
     * handle a exception if the methods
     * XMLJavaTypeConverter#convertObjectValueToDataValue and
     * XMLJavaTypeConverter#convertDataValueToObjectValue allows XMLMarshaller
     * and XMLUnmarshaller arguments to be passed as null values. The current
     * code doesn't handle that IF the XmlAdapter throws an exception the code
     * in the catch clause doesn't check if the un/marshaller is null and
     * therefore rendering a NPE instead of a ConversionException.
     */

    @Test(expected = ConversionException.class)
    public void testXMLAdapterMarshallerContract(final @Mocked Session session, final @Mocked DatabaseMapping mapping, final @Mocked Login login, final @Mocked Platform platform, final @Mocked ConversionManager mgr) {
        new Expectations() {
            {
                session.getDatasourceLogin();
                result = login;
                login.getDatasourcePlatform();
                result = platform;
                platform.getConversionManager();
                result = mgr;
                mgr.getLoader();
                result = ThrowingXMLAdapter.class.getClassLoader();
            }
        };
        XMLJavaTypeConverter xjtc = new XMLJavaTypeConverter(ThrowingXMLAdapter.class);
        xjtc.initialize(mapping, session);
        xjtc.convertObjectValueToDataValue(new Object(), session, null);
    }

    @Test(expected = ConversionException.class)
    public void testXMLAdapterUnMarshallerContract(final @Mocked Session session, final @Mocked DatabaseMapping mapping, final @Mocked Login login, final @Mocked Platform platform, final @Mocked ConversionManager mgr) {
        new Expectations() {
            {
                session.getDatasourceLogin();
                result = login;
                login.getDatasourcePlatform();
                result = platform;
                platform.getConversionManager();
                result = mgr;
                mgr.getLoader();
                result = ThrowingXMLAdapter.class.getClassLoader();
            }
        };
        XMLJavaTypeConverter xjtc = new XMLJavaTypeConverter(ThrowingXMLAdapter.class);
        xjtc.initialize(mapping, session);
        xjtc.convertDataValueToObjectValue(new Object(), session, null);
    }

    private static class ThrowingXMLAdapter extends XmlAdapter {

        public ThrowingXMLAdapter() {
            super();
        }

        @Override
        public Object unmarshal(Object v) throws Exception {
            throw new Exception();
        }

        @Override
        public Object marshal(Object v) throws Exception {
            throw new Exception();
        }
    }
}
