package ir.map.servicesdk.request;

import androidx.annotation.NonNull;

import java.util.List;

import ir.map.servicesdk.enums.DistanceMatrixOutputType;
import ir.map.servicesdk.model.base.BaseModel;
import ir.map.servicesdk.model.inner.DistanceMatrixPointRequest;

public class DistanceMatrixRequest extends BaseModel {

    private List<DistanceMatrixPointRequest> origins;
    private List<DistanceMatrixPointRequest> destinations;
    private boolean sorted;
    private String filter;

    private DistanceMatrixRequest(
            List<DistanceMatrixPointRequest> origins,
            List<DistanceMatrixPointRequest> destinations,
            boolean sorted,
            String filter) {
        this.origins = origins;
        this.destinations = destinations;
        this.sorted = sorted;
        this.filter = filter;
    }

    public String getOrigins() {
        StringBuilder tempOrigins = new StringBuilder();

        for (int i = 0; i < origins.size(); i++)
            tempOrigins
                    .append(origins.get(i).getId())
                    .append(",")
                    .append(origins.get(i).getLatitude())
                    .append(",")
                    .append(origins.get(i).getLongitude())
                    .append((i != origins.size() - 1) ? "%7C" : "");

        return tempOrigins.toString();
    }

    public String getDestinations() {
        StringBuilder tempDestinations = new StringBuilder();

        for (int i = 0; i < destinations.size(); i++)
            tempDestinations
                    .append(destinations.get(i).getId())
                    .append(",")
                    .append(destinations.get(i).getLatitude())
                    .append(",")
                    .append(destinations.get(i).getLongitude())
                    .append((i != destinations.size() - 1) ? "%7C" : "");

        return tempDestinations.toString();
    }

    public boolean isSorted() {
        return sorted;
    }

    public String getFilter() {
        return filter;
    }

    public boolean hasFilter() {
        return filter != null;
    }

    public static class Builder {

        private List<DistanceMatrixPointRequest> origins;
        private List<DistanceMatrixPointRequest> destinations;
        private boolean sorted = false;
        private String filter = null;

        public Builder(List<DistanceMatrixPointRequest> origins, List<DistanceMatrixPointRequest> destinations) {
            if (origins.size() == 0)
                throw new RuntimeException("origins for distanceMatrix api must have at least on point.");
            else if (destinations.size() == 0)
                throw new RuntimeException("destinations for distanceMatrix api must have at least on point.");

            this.origins = origins;
            this.destinations = destinations;
        }

        public Builder sorted(boolean value) {
            this.sorted = value;

            return this;
        }

        public Builder filter(@NonNull DistanceMatrixOutputType outputType) {
            if (outputType != null) {
                this.filter = "type eq " + outputType.toString();
            } else
                throw new RuntimeException("Filter outputType can not be null.");

            return this;
        }

        public DistanceMatrixRequest build() {
            return new DistanceMatrixRequest(origins, destinations, sorted, filter);
        }
    }
}
