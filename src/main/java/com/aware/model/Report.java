package com.aware.model;

// Represents a civic issue report
public class Report {

    private int    id;
    private String issueType;
    private String description;
    private String location;
    private String status;       // PENDING, IN_PROGRESS, COMPLETED, REJECTED
    private String hash;
    private String previousHash;
    private String submittedBy;
    private String submitterEmail;
    private boolean hasImage;    // just a flag so JSP knows whether to show image link

    public Report() {}

    // Getters and Setters
    public int    getId()                      { return id; }
    public void   setId(int id)                { this.id = id; }

    public String getIssueType()               { return issueType; }
    public void   setIssueType(String v)       { this.issueType = v; }

    public String getDescription()             { return description; }
    public void   setDescription(String v)     { this.description = v; }

    public String getLocation()                { return location; }
    public void   setLocation(String v)        { this.location = v; }

    public String getStatus()                  { return status; }
    public void   setStatus(String v)          { this.status = v; }

    public String getHash()                    { return hash; }
    public void   setHash(String v)            { this.hash = v; }

    public String getPreviousHash()            { return previousHash; }
    public void   setPreviousHash(String v)    { this.previousHash = v; }

    public String getSubmittedBy()             { return submittedBy; }
    public void   setSubmittedBy(String v)     { this.submittedBy = v; }

    public String getSubmitterEmail()          { return submitterEmail; }
    public void   setSubmitterEmail(String v)  { this.submitterEmail = v; }

    public boolean isHasImage()                { return hasImage; }
    public void    setHasImage(boolean v)      { this.hasImage = v; }
}
