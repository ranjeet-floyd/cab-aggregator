package com.example.cab.aggregator.client;

import com.example.cab.aggregator.client.bean.ActiveCabBean;
import com.example.cab.aggregator.client.bean.DropBeanResource;
import com.example.cab.aggregator.client.bean.PickUpResourceBean;
import com.example.cab.aggregator.client.bean.RiderBean;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author ranjeet
 */
public class CabServiceImp {

    private final CabService service;

    public CabServiceImp(String baseUrl) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(CabService.class);
    }

    public Collection<ActiveCabBean> getCabs() throws IOException {
        return service.getCabs().execute().body();
    }

    public boolean isAvailable(int cabId) throws IOException {
        return service.isAvailable(cabId).execute().body();
    }

    public Optional<RiderBean> pickRider(PickUpResourceBean pickUpResourceBean) throws IOException {
        RiderBean rider = service.pickRider(pickUpResourceBean).execute().body();
        if (Objects.nonNull(rider)) {
            return Optional.of(rider);
        }
        return Optional.empty();
    }

    public List<RiderBean> drop(DropBeanResource dropBeanResource) throws IOException {
        return service.drop(dropBeanResource).execute().body();
    }

}
