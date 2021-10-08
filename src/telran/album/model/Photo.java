package telran.album.model;

import java.time.LocalDateTime;

public class Photo implements Comparable<Photo> {
	private int albomId;
	private int photoId;
	private String title;
	private String url;
	private LocalDateTime date;

	public Photo(int albomId, int photoId, String title, String url, LocalDateTime date) {
		this.albomId = albomId;
		this.photoId = photoId;
		this.title = title;
		this.url = url;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getAlbomId() {
		return albomId;
	}

	public int getPhotoId() {
		return photoId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Photo [albomId=" + albomId + ", photoId=" + photoId + ", title=" + title + ", url=" + url + ", date="
				+ date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + albomId;
		result = prime * result + photoId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Photo)) {
			return false;
		}
		Photo other = (Photo) obj;
		if (albomId != other.albomId) {
			return false;
		}
		if (photoId != other.photoId) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Photo other) {
		int res = date.compareTo(other.date);
		{
			if (res != 0)
			{
				return res;
			}
		}
		return Integer.compare(photoId,other.photoId);
	}

}
