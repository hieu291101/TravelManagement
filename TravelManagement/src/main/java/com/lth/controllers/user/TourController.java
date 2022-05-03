package com.lth.controllers.user;

import com.lth.pojos.*;
import com.lth.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Controller
@PropertySource("classpath:pagination.properties")
@RequestMapping("")
public class TourController {
    @Autowired
    TourService tourService;
    @Autowired
    TourPlaceService tourPlaceService;
    @Autowired
    TourScheduleService tourScheduleService;
    @Autowired
    TourDepartureService tourDepartureService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    SurchangeService surchangeService;
    @Autowired
    BookingService bookingService;

    @GetMapping ("/tour/{id}")
    public String tourDetail(ModelMap modelMap, @PathVariable("id") int id) {
        Tour tour = tourService.findTourById(id);
        List<TourPlace> tourPlace = tourPlaceService.findTourPlaceByTourId(id);
        Object[] rating = feedbackService.getRatingByTourId(id);
        List<TourSchedule> tourSchedules = tourScheduleService.findTourScheduleByTourId(id);
        List<TourDeparture> tourDepartures = tourDepartureService.findTourDepartureByTourId(id);

        modelMap.put("tour", tour);
        modelMap.put("tourPlace", tourPlace);
        modelMap.put("tourSchedules", tourSchedules);
        modelMap.put("tourDepartures", tourDepartures);
        modelMap.put("booking", new Booking());

        Date minDate = tourDepartures.stream().map(u -> u.getDeparture()).min(Date::compareTo).get();
        modelMap.put("minDate", minDate);
        if(rating == null) {
            rating[0] = 0;
            rating[1] = 5;
            modelMap.put("rating", rating);
        }
        else
            modelMap.put("rating", rating);

        return "user.index.tourdetail";
    }

    @GetMapping("/tour/{id}/abate")
    public String abateTour(ModelMap modelMap, @PathVariable("id") int id) {
        Tour tour = tourService.findTourById(id);
        List<TourDeparture> tourDepartures = tourDepartureService.findTourDepartureByTourId(id);
        List<Surcharge> surcharges = surchangeService.getSurchange();
        Date minDate = tourDepartures.stream().map(u -> u.getDeparture()).min(Date::compareTo).get();

        modelMap.put("minDate", minDate);
        modelMap.put("tour", tour);
        modelMap.put("surchanges", surcharges);
        modelMap.put("booking", new Booking());

        return "user.index.abate";
    }

    @PostMapping("/tour/{id}/abate/announce")
    public String announce(ModelMap modelMap, @PathVariable("id") int id, @ModelAttribute("booking") Booking booking,
                           @RequestParam("abateType") String abateType) {
        Tour tour = tourService.findTourById(id);
        List<TourDeparture> tourDepartures = tourDepartureService.findTourDepartureByTourId(id);
        List<Surcharge> surcharges = surchangeService.getSurchange();
        Date minDate = tourDepartures.stream().map(u -> u.getDeparture()).min(Date::compareTo).get();

        long price = tour.getPrice();
        long adultPrice = (long) Math.round(price / 1000) * 1000
                * booking.getBookingDetail().getNumberAdult();
        long ageGroup511Price = (long) Math.round(price * surcharges.get(1).getPercentage() / 1000) * 1000
                * booking.getBookingDetail().getNumberAgegroup511();
        long ageGroup25Price = (long) Math.round(price * surcharges.get(2).getPercentage() / 1000) * 1000
                * booking.getBookingDetail().getNumberAgegroup25();
        long ageGroup02Price = (long) Math.round(price * surcharges.get(3).getPercentage() / 1000) * 1000
                * booking.getBookingDetail().getNumberAgegroup02();

        String message = "";

//        if (abateType.equals("radioDefault")){
//            booking.getBookingDetail().setIsPayment(false);
//            booking.getBookingDetail().setTotalPrice(price + ageGroup511Price + ageGroup25Price * ageGroup02Price);
////            if (bookingService.addBooking(booking) == true) {
////                message = "Thêm thành công";
////            }
////            else
////                message = "Thêm thất bại";
//
//        }


        modelMap.put("minDate", minDate);
        modelMap.put("tour", tour);
        modelMap.put("surchanges", surcharges);
        modelMap.put("booking", booking);
        modelMap.put("bookingNumberAdult", booking.getBookingDetail().getNumberAdult());
        modelMap.put("message", message);

        return "user.index.abate";
    }
}
