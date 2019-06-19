package control;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import report.ReportException;
import report.Section;
import report.printers.PDFReportPrinter;
import report.sections.ListSection;
import report.sections.TextSection;

public class PrettyPDFReportPrinter extends PDFReportPrinter {
    
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

    @Override
    protected List getList(ListSection listSection) throws ReportException {
        return super.getList(listSection);
    }

    @Override
    protected Chunk getChunk(TextSection section) {
        return super.getChunk(section);
    }

    @Override
    protected java.util.List<Element> parse(java.util.List<Section> sections) throws ReportException {
        return super.parse(sections);
    }
    
    
    
}
