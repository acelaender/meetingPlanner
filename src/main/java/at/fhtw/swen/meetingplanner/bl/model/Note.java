package at.fhtw.swen.meetingplanner.bl.model;


public class Note {

    private int id;
    private String title;
    private String content;
    private Meeting meeting;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Note(String title, String content, Meeting meeting) {
        this.title = title;
        this.content = content;
        this.meeting = meeting;
    }
}
