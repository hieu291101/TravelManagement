/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lth.pojos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author PC
 */
@Entity
@XmlRootElement
@Table(name = "tour")
@NamedQueries({
        @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t"),
        @NamedQuery(name = "Tour.findById", query = "SELECT t FROM Tour t WHERE t.id = :id"),
        @NamedQuery(name = "Tour.findByName", query = "SELECT t FROM Tour t WHERE t.name = :name"),
        @NamedQuery(name = "Tour.findByPrice", query = "SELECT t FROM Tour t WHERE t.price = :price"),
        @NamedQuery(name = "Tour.findByDescription", query = "SELECT t FROM Tour t WHERE t.description = :description")})

public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "price", nullable = false, precision = 12)
    private BigDecimal price;

    @Column(name = "image")
    private String image;
    @NotNull
    @Column(name = "limit_customer")
    private Integer limit_customer;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "duration_id", referencedColumnName = "id")
    @ManyToOne
    private Duration duration;
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    @ManyToOne
    private Trip trip;
    @OneToMany(mappedBy = "tour")
    private Collection<Feedback> feedbackCollection;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;


    @OneToMany(mappedBy = "tour")
    private Set<TourSchedule> tourSchedules = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tour")
    private Set<TourDeparture> tourDepartures = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tour")
    private Set<TourPlace> tourPlaces = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tour")
    private Set<Booking> bookings = new LinkedHashSet<>();

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<TourPlace> getTourPlaces() {
        return tourPlaces;
    }

    public void setTourPlaces(Set<TourPlace> tourPlaces) {
        this.tourPlaces = tourPlaces;
    }

    public Set<TourDeparture> getTourDepartures() {
        return tourDepartures;
    }

    public void setTourDepartures(Set<TourDeparture> tourDepartures) {
        this.tourDepartures = tourDepartures;
    }

    public Set<TourSchedule> getTourSchedules() {
        return tourSchedules;
    }

    public void setTourSchedules(Set<TourSchedule> tourSchedules) {
        this.tourSchedules = tourSchedules;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tour() {
    }

    public Tour(Integer id) {
        this.id = id;
    }

    public Tour(Integer id, String name, BigDecimal price, String image, Integer limit_customer, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.limit_customer = limit_customer;
        this.description = description;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLimit_customer() {
        return limit_customer;
    }

    public void setLimit_customer(Integer limit_customer) {
        this.limit_customer = limit_customer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration durationId) {
        this.duration = durationId;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip tripId) {
        this.trip = tripId;
    }

    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lth.pojos.Tour[ id=" + id + " ]";
    }

}
