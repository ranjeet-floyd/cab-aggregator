package com.example.cab.aggregator.client;

import com.example.cab.aggregator.client.bean.ActiveCabBean;
import com.example.cab.aggregator.client.bean.DropBeanResource;
import com.example.cab.aggregator.client.bean.PickUpResourceBean;
import com.example.cab.aggregator.client.bean.RiderBean;
import java.util.Collection;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Cab resource urls map
 *
 * @author ranjeet
 */
public interface CabService {

    @GET("cab")
    public Call<Collection<ActiveCabBean>> getCabs();

    @GET("cab/{cabId}")
    public Call<Boolean> isAvailable(@Path("cabId") int cabId);

    @POST("cab/pick")
    public Call<RiderBean> pickRider(@Body PickUpResourceBean pickUpResourceBean);

    @POST("cab/drop")
    public Call<List<RiderBean>> drop(@Body DropBeanResource dropBeanResource);
}
