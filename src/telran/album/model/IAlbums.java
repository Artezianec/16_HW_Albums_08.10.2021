package telran.album.model;

import java.time.LocalDate;

public interface IAlbums {
	boolean addPhoto(Photo photo);

	boolean removePhoto(int photoId, int albomId);

	boolean updatePhoto(int photoId, int albomId, String url);

	Photo getPhotoFromAlbum(int photoId, int albomId);

	Photo[] getAllPhotoFromAlbum(int albomId);

	Photo[] getPhotoBeetwenDate(LocalDate dateFrom, LocalDate dateTo);
	
	int size();

}
