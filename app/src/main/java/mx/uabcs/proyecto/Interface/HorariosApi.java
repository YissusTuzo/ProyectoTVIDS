package mx.uabcs.proyecto.Interface;

import java.util.List;

import mx.uabcs.proyecto.Horariosc;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface HorariosApi {

    @GET("/api/schedules/{id}")
    Call<List<Horariosc>> getHorarios(@Path("id")int id);
}
