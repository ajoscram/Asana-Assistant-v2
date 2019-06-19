package asana_assistant_2.control;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.time.LocalDate;
import java.util.ArrayList;
import asana_assistant_1.report.ReportException;
import asana_assistant_1.report.Section;
import asana_assistant_1.report.printers.PDFReportPrinter;
import asana_assistant_1.report.sections.ListSection;
import asana_assistant_1.report.sections.TextSection;

public class ProjectPDFReportPrinter extends PDFReportPrinter {
    
    //report tags
    private static final String PROJECT_NAME = "name";
    private static final String PROJECT_ID = "id";
    private static final String REPORT_BY = "by";
    private static final String REPORT_START = "start";
    private static final String REPORT_FINISH = "finish";
    
    //development tags
    private static final String DEVELOPMENT_DESCRIPTION = "description";
    private static final String DEVELOPMENT_WORK = "work";
    private static final String DEVELOPMENT_EVIDENCE_TITLE = "evidence";
    
    //task tags
    private static final String TASKS = "tasks";
    private static final String TASK_TYPE = "type";
    private static final String TASK_PARENT = "parent";
    private static final String TASK_ASIGNEE = "asignee";
    private static final String TASK_DUE = "due";
    private static final String TASK_COMPLETED = "completed";

    //fonts
    private static final Font PROJECT_NAME_FONT = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
    private static final Font REPORT_PARAMETER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static final Font REPORT_DATE_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
    private static final Font REGULAR_FONT = new Font(Font.FontFamily.HELVETICA, 12);
    
    @Override
    protected List getList(ListSection listSection) throws ReportException {
        List list = super.getList(listSection);
        return list;
    }

    @Override
    protected Chunk getChunk(TextSection section) {
        if(section.getName() == null)
            return new Chunk(section.getText(), REGULAR_FONT);
        if(section.getName().equals(PROJECT_NAME))
            return new Chunk(section.getText(), PROJECT_NAME_FONT);
        else if(section.getName().equals(PROJECT_ID))
            return new Chunk("ID: ", REPORT_PARAMETER_FONT);
        else if(section.getName().equals(REPORT_BY))
            return new Chunk("Asignee: ", REPORT_PARAMETER_FONT);
        else if(section.getName().equals(REPORT_START))
            return new Chunk("Starting: ", REPORT_PARAMETER_FONT);
        else if(section.getName().equals(REPORT_FINISH))
            return new Chunk("Ending: ", REPORT_PARAMETER_FONT);
        else
            return super.getChunk(section);
    }
    
    protected Paragraph getListTitle(ListSection section){
        Paragraph paragraph = new Paragraph();
        if(section.getName().equals(TASKS)){
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Chunk("Tasks:", REPORT_PARAMETER_FONT));
        }
        else
            paragraph.add(new Chunk(section.getName(), REGULAR_FONT));
        return paragraph;
    }
    
    @Override
    protected java.util.List<Element> parse(java.util.List<Section> sections) throws ReportException {
        ArrayList<Element> elements = new ArrayList();
        for(Section section : sections){
            if(section instanceof ListSection){
                if(section.getName() != null)
                    elements.add(getListTitle((ListSection)section));
                elements.add(getList((ListSection)section));
            }
            else if(section instanceof TextSection){
                Paragraph text = new Paragraph();
                text.add(getChunk((TextSection) section));
                if(section.getName() == PROJECT_NAME)
                {
                    //adding date before title
                    Paragraph date = new Paragraph();
                    date.add(new Chunk(LocalDate.now().toString(), REPORT_DATE_FONT));
                    date.setAlignment(Paragraph.ALIGN_RIGHT);
                    elements.add(date);
                    
                    //adding the line separator after the title
                    LineSeparator separator = new LineSeparator();
                    separator.setOffset((float)4.5);
                    text.add(Chunk.NEWLINE);
                    text.add(separator);
                    text.add(Chunk.NEWLINE);
                }
                if(section.getName() == PROJECT_ID ||
                   section.getName() == REPORT_BY || 
                   section.getName() == REPORT_START || 
                   section.getName() == REPORT_FINISH){
                    TextSection textSection = (TextSection)section;
                    text.add(new Chunk(textSection.getText(), REGULAR_FONT));
                }
                elements.add(text);
            }
            else
                throw new ReportException(ReportException.Type.UNKNOWN_SECTION_TYPE);
        }
        return elements;
    }
    
    
    
}
