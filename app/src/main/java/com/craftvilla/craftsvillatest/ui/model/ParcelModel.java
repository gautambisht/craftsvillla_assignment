package com.craftvilla.craftsvillatest.ui.model;

import com.google.gson.annotations.SerializedName;

public class ParcelModel {
    private String address;
    private String applicant;
    @SerializedName("approved")
    private String approvedDate;
    private String block;
    private String blocklot;
    private String cnn;
    @SerializedName("dayshours")
    private String daysHours;
    @SerializedName("expirationdate")
    private String expirationDate;
    @SerializedName("facilitytype")
    private String facilityType;
    @SerializedName("fooditems")
    private String foodItems;
    private String latitude;
    private LocationModel location;
    @SerializedName("locationdescription")
    private String locationDescription;
    private String longitude;
    private String lot;
    @SerializedName("objectid")
    private String objectId;
    private String permit;
    @SerializedName("priorpermit")
    private String priorPermit;
    private String received;
    private String schedule;
    private String status;
    private String x;
    private String y;

    public String getAddress() {
        return address;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public String getBlock() {
        return block;
    }

    public String getBlocklot() {
        return blocklot;
    }

    public String getCnn() {
        return cnn;
    }

    public String getDaysHours() {
        return daysHours;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public String getFoodItems() {
        return foodItems;
    }

    public String getLatitude() {
        return latitude;
    }

    public LocationModel getLocation() {
        return location;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLot() {
        return lot;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getPermit() {
        return permit;
    }

    public String getPriorPermit() {
        return priorPermit;
    }

    public String getReceived() {
        return received;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getStatus() {
        return status;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}
