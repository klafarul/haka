package services;


import com.google.gson.Gson;
import controllers.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pojo.CarPojo;
import pojo.PersonPojo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "classpath:contextMVC.xml"
        }
)
public class TestDBServiceMockMVC {

    private MockMvc mockMvc;

    @Autowired
    private Controller controller;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testAddBadPersonEmptyID() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setName("MAsk");
        personPojo.setBirthdate("12.12.1998");

        Gson gson = new Gson();
        String json = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testAddBadPersonNullID() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(0);
        personPojo.setName("MAsk");
        personPojo.setBirthdate("12.12.1998");

        Gson gson = new Gson();
        String json = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testAddBadPersonNullName() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(10);
        personPojo.setBirthdate("12.12.1998");

        Gson gson = new Gson();
        String json = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testAddBadPersonNullBirthdate() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(0);
        personPojo.setName("MAsk");

        Gson gson = new Gson();
        String json = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testAddBadPersonIncorrectBirthdate() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(0);
        personPojo.setName("MAsk");
        personPojo.setBirthdate("12-12-1998");

        Gson gson = new Gson();
        String json = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testAddBadCarEmptyNullModel() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(-310);
        personPojo.setName("Mask");
        personPojo.setBirthdate("12.12.1998");

        Gson gson = new Gson();
        String jsonPerson = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson))
                .andExpect(status().isOk());

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-100);
        carPojo.setHorsepower(100);
        carPojo.setOwnerId(-100);

        String jsonCar = gson.toJson(carPojo);
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content(jsonCar))
                .andExpect(status().isBadRequest());

        carPojo = new CarPojo();
        carPojo.setId(-110);
        carPojo.setModel("");
        carPojo.setHorsepower(100);
        carPojo.setOwnerId(-100);

        jsonCar = gson.toJson(carPojo);
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content(jsonCar))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testAddBadCarEmptyNullOwnerId() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(-100);
        personPojo.setName("Mask");
        personPojo.setBirthdate("12.12.1998");

        Gson gson = new Gson();
        String jsonPerson = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson))
                .andExpect(status().isOk());

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-100);
        carPojo.setModel("BMW-X5");
        carPojo.setHorsepower(100);

        String jsonCar = gson.toJson(carPojo);
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content(jsonCar))
                .andExpect(status().isBadRequest());

        carPojo = new CarPojo();
        carPojo.setId(-110);
        carPojo.setModel("BMW-X5");
        carPojo.setHorsepower(100);
        carPojo.setOwnerId(0);

        jsonCar = gson.toJson(carPojo);
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content(jsonCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testStatistics() throws Exception{
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(-100);
        personPojo.setName("Mask");
        personPojo.setBirthdate("12.12.1998");

        Gson gson = new Gson();
        String jsonPerson = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson))
                .andExpect(status().isOk());

        CarPojo carPojo = new CarPojo();
        carPojo.setId(-100);
        carPojo.setModel("BMW-X5");
        carPojo.setHorsepower(100);
        carPojo.setOwnerId(-100);

        String jsonCar = gson.toJson(carPojo);
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content(jsonCar))
                .andExpect(status().isOk());

        carPojo = new CarPojo();
        carPojo.setId(-120);
        carPojo.setModel("Audi-A5");
        carPojo.setHorsepower(200);
        carPojo.setOwnerId(-100);

        jsonCar = gson.toJson(carPojo);
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content(jsonCar))
                .andExpect(status().isOk());

        mockMvc.perform(get("/statistics")).andExpect(status().isOk()).andExpect(jsonPath("$.personcount").value(1));
        mockMvc.perform(get("/statistics")).andExpect(status().isOk()).andExpect(jsonPath("$.carcount").value(2));
        mockMvc.perform(get("/statistics")).andExpect(status().isOk()).andExpect(jsonPath("$.uniquevendorcount").value(2));
    }

    @Test
    public void testClear() throws Exception {
        PersonPojo personPojo = new PersonPojo();
        personPojo.setId(-300);
        personPojo.setName("Mask");
        personPojo.setBirthdate("12.12.1998");

        Gson gson = new Gson();
        String jsonPerson = gson.toJson(personPojo);
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/clear")).andReturn();

        mockMvc.perform(get("/personwithcars").param("personid", String.valueOf(personPojo.getId()))).andExpect(status().isNotFound());

    }
}
