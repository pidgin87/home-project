package com.smirnoff.home.platform.backup.camel.listener.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionMessageHeaderWriterProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        exchange.getIn().setHeader("exception", ExceptionUtils.getStackTrace(exception));
    }
}
