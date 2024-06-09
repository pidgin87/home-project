package com.smirnoff.home.finance.history.export;

import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.springframework.stereotype.Service;

@Service
public class KiwibankExportService {

    public void export(String filePath) {
        PDFParserConfig pdfParserConfig = new PDFParser().getPDFParserConfig();
        new ParseContext().set(PDFParserConfig.class, pdfParserConfig);
    }

}
