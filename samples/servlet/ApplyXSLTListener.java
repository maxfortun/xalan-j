/*****************************************************************************************************
 * $Id$
 *
 * Copyright (c) 1998-1999 Lotus Corporation, Inc. All Rights Reserved.
 *				This software is provided without a warranty of any kind.
 *
 * $State$
 *****************************************************************************************************/
package servlet;

import java.io.*;
import org.xml.sax.*;
import org.apache.xalan.utils.DefaultErrorHandler;

/*****************************************************************************************************
 * ApplyXSLTListener provides a buffered listener essential for capturing, and then subsequently
 * reporting, XML and XSL processor messages which may be of use in debugging XML+XSL processed at
 * the server.
 *
 * @author Spencer Shepard (sshepard@us.ibm.com)
 * @author R. Adam King (rak@us.ibm.com)
 * @author Tom Rowe (trowe@us.ibm.com)
 *
 *****************************************************************************************************/

public class ApplyXSLTListener extends DefaultErrorHandler implements ErrorHandler
{

    /**
      * Output stream
      */
    private ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    /**
      * Buffered output stream
      */
    public PrintWriter out = null;

    /**
      * Constructor.
      */
    public ApplyXSLTListener(String identifier)
    {
	super(identifier);
    out = new PrintWriter(new BufferedOutputStream(outStream), true);
    }

    /**
      * Receive notification of a warning.
      *
      * @param spe The warning information encapsulated in a SAX parse exception.
      */
    public void warning(SAXParseException spe)
    {
	out.println("Parser Warning: " + spe.getMessage());
    }

    /**
      * Receive notification of a recoverable error.
      *
      * @param spe The error information encapsulated in a SAX parse exception.
      */
    public void error(SAXParseException spe)
    {
	out.println("Parser Error: " + spe.getMessage());
    }

    /**
      * Receive notification of a non-recoverable error.
      *
      * @param spe The error information encapsulated in a SAX parse exception.
      * @exception SAXException Always thrown
      */
    public void fatalError(SAXParseException spe)
    throws SAXException
    {
	out.println("Parser Fatal Error: " + spe.getMessage());
	throw spe;
    }

    /**
      * Returns the buffered processing message(s).
      * @return Buffered processing message(s)
      */
    public String getMessage()
    {
	return outStream.toString();
    }
}

