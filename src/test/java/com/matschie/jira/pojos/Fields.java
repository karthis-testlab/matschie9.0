package com.matschie.jira.pojos;

public class Fields {

	private Project project;
	private Issuetype issuetype;
	private String summary;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Issuetype getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(Issuetype issuetype) {
		this.issuetype = issuetype;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}