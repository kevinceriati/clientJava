package com.RottenCar.client.controller;

import com.RottenCar.client.form.CarForm;
import com.RottenCar.client.model.Car;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarModelController {

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String carList(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String ressourceUrl = "http://localhost:8080/car";
        List<Car> cars = restTemplate.getForObject(ressourceUrl, List.class);
        model.addAttribute("cars", cars);
        return "carList";
    }


    @RequestMapping(value = "/car/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String ressourceUrl = "http://localhost:8080/car/" + id;
        Car car = restTemplate.getForObject(ressourceUrl, Car.class);
        model.addAttribute("car", car);
        return "car";
    }


    //Show view carForm
    @RequestMapping(value = {"/addCar"}, method = RequestMethod.GET)
    public String showAddCarPage(Model model) {
        CarForm carForm = new CarForm();
        model.addAttribute("carForm", carForm);
        return "addCar";
    }


    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public String save(@ModelAttribute CarForm car, Model model) {

        Car newCar = new Car();
        String url = "http://localhost:8080/car";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        if (car != null) {
            newCar.setBrand(car.getBrand());
            newCar.setName(car.getName());
            HttpEntity<Car> request = new HttpEntity(newCar, headers);
            newCar = restTemplate.postForObject(url, request, Car.class);
        }
        model.addAttribute("car", newCar);
        return "redirect:/car";
    }


    @RequestMapping(value = "/car/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute CarForm carForm, @PathVariable int id, Model model) {

        Car modifiedCar = new Car();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        modifiedCar.setId(id);
        modifiedCar.setName(carForm.getName());
        modifiedCar.setBrand(carForm.getBrand());

        HttpEntity<Car> request = new HttpEntity(modifiedCar, headers);
        String url = "http://localhost:8080/car/" + id;
        restTemplate.exchange(url, HttpMethod.PUT, request, Car.class);
        model.addAttribute("car", modifiedCar);
        return "redirect:/car";
    }


    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String ressourceUrl = "http://localhost:8080/car/" + id;
        restTemplate.delete(ressourceUrl);
        return "/carList";

/*        Car car = restTemplate.getForObject(ressourceUrl, Car.class);
        model.addAttribute("car", car);
        return "car";*/
    }




    /*
    @RequestMapping(value="/car/{id}", method= RequestMethod.PUT)
    @ResponseBody
    public Car update(@PathVariable int id, @RequestBody Car car) {
        car.setId(id);
        return dao.update(car);
    }

    @RequestMapping(value="/car/{id}", method= RequestMethod.DELETE)
    public void delete (@PathVariable int id) {
        dao.delete(id);
    }*/
}
