package org.pursuit.viewpagerappfromscratch.network;

import org.pursuit.viewpagerappfromscratch.model.ZodiacList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ZodiacServices {
    String END_POINT = "JDVila/storybook/master/zodiac.json";

    @GET(END_POINT)
    Observable<ZodiacList> getZodiac();
}
