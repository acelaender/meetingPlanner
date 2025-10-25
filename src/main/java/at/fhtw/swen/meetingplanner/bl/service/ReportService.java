package at.fhtw.swen.meetingplanner.bl.service;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;

import java.io.File;
import java.io.IOException;

@Service
public class ReportService {
    private NoteService noteService;

    public ReportService(NoteService noteService) {
        this.noteService = noteService;
    }

    public File generateMeetingReport(Meeting meeting, String filepath) throws IOException {
        File file = new File(filepath);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        try {
            document.add(new Paragraph("Title: " + meeting.getTitle()));
            document.add(new Paragraph("Agenda: " + meeting.getAgenda()));
            document.add(new Paragraph("Start: " + meeting.getStartTime()));
            document.add(new Paragraph("End: " + meeting.getEndTime()));
            document.add(new Paragraph("Notes:"));
            noteService.getMeetingNotes(meeting).forEach(note ->
                    document.add(new Paragraph("- " + note.getTitle() + ", " + note.getContent())));
            document.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return file;
    }
}
