package xyz.janficko.moboole.model;

public class MockModel {

	private int id;
	private String imageUrl;
	private String title;
	private String content;
	private String user;
	private int votes;
	private int comments;
	private String subreddit;
	private String source;
	private String submitted;
	private String linkFlair;

	public MockModel() {
	}

	public MockModel(int id, String imageUrl, String title, String content, String user, int votes, int comments, String subreddit, String source, String submitted, String linkFlair) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.title = title;
		this.content = content;
		this.user = user;
		this.votes = votes;
		this.comments = comments;
		this.subreddit = subreddit;
		this.source = source;
		this.submitted = submitted;
		this.linkFlair = linkFlair;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getSubreddit() {
		return subreddit;
	}

	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getLinkFlair() {
		return linkFlair;
	}

	public void setLinkFlair(String linkFlair) {
		this.linkFlair = linkFlair;
	}
}
