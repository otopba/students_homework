package ru.ok.technopolis.students.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import ru.ok.technopolis.students.R;

public class MalePhotoRepository implements PhotoRepository {
    public static final MalePhotoRepository Instance = new MalePhotoRepository();

    private List<Integer> malePhotos;

    private MalePhotoRepository() {
        malePhotos = new ArrayList<Integer>() {{
            this.add(R.drawable.male_1);
            this.add(R.drawable.male_2);
            this.add(R.drawable.male_3);
        }};
    }


    @Override
    public int getPhotoInRepository() throws NoSuchElementException {
        if (malePhotos.isEmpty()) {
            throw new NoSuchElementException();
        }
        int indexMalePhoto = new Random().nextInt(malePhotos.size());
        int malePhoto = malePhotos.get(indexMalePhoto);
        malePhotos.remove(indexMalePhoto);
        return malePhoto;
    }

    @Override
    public void putPhotoInRepository(int photo) {
        malePhotos.add(photo);
    }
}
