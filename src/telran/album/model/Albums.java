package telran.album.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.function.Predicate;

public class Albums implements IAlbums {
    private Photo[] photos;
    private int size;

    public Albums(int capacity) {
        photos = new Photo[capacity];
    }

    @Override
    public boolean addPhoto(Photo photo) {
        if (size == photos.length || getPhotoFromAlbum(photo.getPhotoId(), photo.getAlbomId()) != null) {
            return false;
        }
        int index = Arrays.binarySearch(photos, 0, size, photo);
        index = index >= 0 ? index : -index - 1;
        System.arraycopy(photos, index, photos, index + 1, size - index);
        photos[index] = photo;
        size++;
        return true;
    }

    @Override
    public boolean removePhoto(int photoId, int albomId) {
        int index = searchIndex(photoId, albomId);
        if (index < 0) {
            return false;
        }
        System.arraycopy(photos, index + 1, photos, index, size - index - 1);
        size--;
        return true;
    }

    private int searchIndex(int photoId, int albomId) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbomId() == albomId && photos[i].getPhotoId() == photoId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean updatePhoto(int photoId, int albomId, String url) {
        Photo photo = getPhotoFromAlbum(photoId, albomId);
        if (photo == null) {
            return false;
        }
        photo.setUrl(url);
        return true;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albomId) {
        int index = searchIndex(photoId, albomId);
        if (index < 0) {
            return null;
        }
        return photos[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albomId) {
        return getPhotosByPredicate(p -> p.getAlbomId() == albomId);
    }

    @Override
    public Photo[] getPhotoBeetwenDate(LocalDate dateFrom, LocalDate dateTo) {
        Photo fake = new Photo(0,Integer.MIN_VALUE,null,null,dateFrom.atStartOfDay());
        int from = Arrays.binarySearch(photos,0,size,fake);
        from = from >= 0 ? from : -from -1;
        fake = new Photo(0,Integer.MAX_VALUE,null,null,dateTo.atTime(LocalTime.MAX));
        int to = Arrays.binarySearch(photos,0,size,fake);
        to = to >= 0 ? to : -to -1;
        return Arrays.copyOfRange(photos,from,to);
/*        return getPhotosByPredicate(p -> p.getDate().isAfter(dateFrom.atStartOfDay())
                && p.getDate().isBefore(dateTo.atTime(LocalTime.MAX)));*/
    }

    private Photo[] getPhotosByPredicate(Predicate<Photo> predicate) {
        int count = 0;
        Photo[] res = new Photo[size];
        for (int i = 0; i < size; i++) {
            if (predicate.test(photos[i])) {
                res[count++] = photos[i];
            }
        }
        return Arrays.copyOf(res, count);
    }

}
