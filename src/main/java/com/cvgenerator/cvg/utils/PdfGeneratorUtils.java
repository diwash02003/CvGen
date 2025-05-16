package com.cvgenerator.cvg.utils;

import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;


@Slf4j
@Component
public class PdfGeneratorUtils {

    public static void generatePdfFromHtml(String htmlContent, String outputFileName) {
        try {
            Pdf pdf = new Pdf();
            pdf.addPageFromString(htmlContent);
            pdf.addParam(new Param("--page-size", "A4"));
            pdf.addParam(new Param("--margin-top", "15mm"));
            pdf.addParam(new Param("--margin-bottom", "15mm"));
            pdf.addParam(new Param("--margin-left", "10mm"));
            pdf.addParam(new Param("--margin-right", "10mm"));
            pdf.saveAs(outputFileName);

            log.info("PDF generated successfully at {}", outputFileName);
        } catch (IOException e) {
            log.error("Error generating PDF", e);
            throw new RuntimeException("Error generating PDF", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parseThymeleafTemplate(String templateName, Context context) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(templateName, context);
    }
}
