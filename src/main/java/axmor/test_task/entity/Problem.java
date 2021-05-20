package axmor.test_task.entity;


public class Problem {

    private int id;

    private String name;

    private String status = "Created";

    private String author;

    private String description;

    public String date;

    public String dateTime;


    public Problem() {
    }

    public Problem(String name, String status, String author, String description) {
        this.name = name;
        this.status = status;
        this.author = author;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "status=" + status +
                '}';
    }
}
