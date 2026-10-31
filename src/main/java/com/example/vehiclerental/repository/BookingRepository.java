package com.example.vehiclerental.repository;
import com.example.vehiclerental.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.vehiclerental.domain.BookingStatus;
import com.example.vehiclerental.domain.Vehicle;
import org.springframework.data.jpa.repository.Modifying;
import java.util.UUID;
import java.util.List;
import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByStatusOrderByPickupDateAsc(BookingStatus status);
    List<Booking> findByPickupDateBetweenOrderByPickupDateAsc(LocalDate startDate, LocalDate endDate);
    boolean existsByVehicleAndPickupDateAndStatusNot(Vehicle vehicle, LocalDate Date, BookingStatus status);
    @Query ("""
        select b.status, count(b) from Booking b group by b.status
        """)
        List<Object[]> findBookingCountByStatus();
        @Modifying 
        @Query("""
            update Booking b set b.status = :newStatus
            where b.vehicle.id = :vehicleId
            and b.pickupDate = :date
            and b.status <> :excludeStatus
            """) 
            int cancelBookingByVehicleIdAndDate(@Param("vehicleId") UUID vehicleId,@Param("date") LocalDate date, 
            @Param("newStatus") BookingStatus newStatus, @Param("excludeStatus") BookingStatus excludeStatus);
    }
    
