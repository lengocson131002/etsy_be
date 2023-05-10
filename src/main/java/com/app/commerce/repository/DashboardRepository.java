package com.app.commerce.repository;

import com.app.commerce.entity.Dashboard;
import com.app.commerce.repository.projections.DashboardRevenueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    @Query("SELECT COALESCE(case " +
            "   WHEN ?1 = 'TODAY' THEN sum(today.orders) " +
            "   WHEN ?1 = 'YESTERDAY' THEN sum(yesterday.orders) " +
            "   WHEN ?1 = 'LAST_7' THEN sum(last7.orders) " +
            "   WHEN ?1 = 'LAST_30' THEN sum(last30.orders) " +
            "   WHEN ?1 = 'THIS_MONTH' THEN sum(thisMonth.orders) " +
            "   WHEN ?1 = 'THIS_YEAR' THEN sum(thisYear.orders) " +
            "   WHEN ?1 = 'LAST_YEAR' THEN sum(lastYear.orders) " +
            "   WHEN ?1 = 'ALL_TIME' THEN sum(allTime.orders) " +
            "   ELSE 0 " +
            "END, 0) " +
            "AS orderCount " +
            "FROM Shop shop " +
            "JOIN shop.todayDashboard today " +
            "JOIN shop.yesterdayDashboard yesterday " +
            "JOIN shop.last7Dashboard last7 " +
            "JOIN shop.last30Dashboard last30 " +
            "JOIN shop.thisMonthDashboard thisMonth " +
            "JOIN shop.thisYearDashboard thisYear " +
            "JOIN shop.lastYearDashboard lastYear " +
            "JOIN shop.allTimeDashboard allTime")
    Long countOrder(String dateRange);

    @Query("SELECT COALESCE(case " +
            "   WHEN ?1 = 'TODAY' THEN sum(today.visits) " +
            "   WHEN ?1 = 'YESTERDAY' THEN sum(yesterday.visits) " +
            "   WHEN ?1 = 'LAST_7' THEN sum(last7.visits) " +
            "   WHEN ?1 = 'LAST_30' THEN sum(last30.visits) " +
            "   WHEN ?1 = 'THIS_MONTH' THEN sum(thisMonth.visits) " +
            "   WHEN ?1 = 'THIS_YEAR' THEN sum(thisYear.visits) " +
            "   WHEN ?1 = 'LAST_YEAR' THEN sum(lastYear.visits) " +
            "   WHEN ?1 = 'ALL_TIME' THEN sum(allTime.visits) " +
            "   ELSE 0 END, 0) " +
            "AS visitCount " +
            "FROM Shop shop " +
            "JOIN shop.todayDashboard today " +
            "JOIN shop.yesterdayDashboard yesterday " +
            "JOIN shop.last7Dashboard last7 " +
            "JOIN shop.last30Dashboard last30 " +
            "JOIN shop.thisMonthDashboard thisMonth " +
            "JOIN shop.thisYearDashboard thisYear " +
            "JOIN shop.lastYearDashboard lastYear " +
            "JOIN shop.allTimeDashboard allTime")
    Long countVisit(String dateRange);

    @Query("SELECT " +
            "shop.currencyCode as currencyCode, " +
            "shop.currencySymbol as currencySymbol, " +
            " case " +
            "   WHEN ?1 = 'TODAY' THEN sum(today.revenue) " +
            "   WHEN ?1 = 'YESTERDAY' THEN sum(yesterday.revenue) " +
            "   WHEN ?1 = 'LAST_7' THEN sum(last7.revenue) " +
            "   WHEN ?1 = 'LAST_30' THEN sum(last30.revenue) " +
            "   WHEN ?1 = 'THIS_MONTH' THEN sum(thisMonth.revenue) " +
            "   WHEN ?1 = 'THIS_YEAR' THEN sum(thisYear.revenue) " +
            "   WHEN ?1 = 'LAST_YEAR' THEN sum(lastYear.revenue) " +
            "   WHEN ?1 = 'ALL_TIME' THEN sum(allTime.revenue) " +
            "   ELSE 0 END " +
            "AS value " +
            "FROM Shop shop " +
            "JOIN shop.todayDashboard today " +
            "JOIN shop.yesterdayDashboard yesterday " +
            "JOIN shop.last7Dashboard last7 " +
            "JOIN shop.last30Dashboard last30 " +
            "JOIN shop.thisMonthDashboard thisMonth " +
            "JOIN shop.thisYearDashboard thisYear " +
            "JOIN shop.lastYearDashboard lastYear " +
            "JOIN shop.allTimeDashboard allTime " +
            "GROUP BY shop.currencyCode, shop.currencySymbol")
    List<DashboardRevenueProjection> countRevenue(String dateRange);

    @Query("SELECT COALESCE(case " +
            "   WHEN ?1 = 'TODAY' THEN sum(today.orders) " +
            "   WHEN ?1 = 'YESTERDAY' THEN sum(yesterday.orders) " +
            "   WHEN ?1 = 'LAST_7' THEN sum(last7.orders) " +
            "   WHEN ?1 = 'LAST_30' THEN sum(last30.orders) " +
            "   WHEN ?1 = 'THIS_MONTH' THEN sum(thisMonth.orders) " +
            "   WHEN ?1 = 'THIS_YEAR' THEN sum(thisYear.orders) " +
            "   WHEN ?1 = 'LAST_YEAR' THEN sum(lastYear.orders) " +
            "   WHEN ?1 = 'ALL_TIME' THEN sum(allTime.orders) " +
            "   ELSE 0 " +
            "END, 0) " +
            "AS orderCount " +
            "FROM Shop shop " +
            "JOIN shop.todayDashboard today " +
            "JOIN shop.yesterdayDashboard yesterday " +
            "JOIN shop.last7Dashboard last7 " +
            "JOIN shop.last30Dashboard last30 " +
            "JOIN shop.thisMonthDashboard thisMonth " +
            "JOIN shop.thisYearDashboard thisYear " +
            "JOIN shop.lastYearDashboard lastYear " +
            "JOIN shop.allTimeDashboard allTime " +
            "WHERE ?2 IS NULL OR shop.status = ?2")
    Long countOrder(String dateRange, String status);

    @Query("SELECT COALESCE(case " +
            "   WHEN ?1 = 'TODAY' THEN sum(today.visits) " +
            "   WHEN ?1 = 'YESTERDAY' THEN sum(yesterday.visits) " +
            "   WHEN ?1 = 'LAST_7' THEN sum(last7.visits) " +
            "   WHEN ?1 = 'LAST_30' THEN sum(last30.visits) " +
            "   WHEN ?1 = 'THIS_MONTH' THEN sum(thisMonth.visits) " +
            "   WHEN ?1 = 'THIS_YEAR' THEN sum(thisYear.visits) " +
            "   WHEN ?1 = 'LAST_YEAR' THEN sum(lastYear.visits) " +
            "   WHEN ?1 = 'ALL_TIME' THEN sum(allTime.visits) " +
            "   ELSE 0 END, 0) " +
            "AS visitCount " +
            "FROM Shop shop " +
            "JOIN shop.todayDashboard today " +
            "JOIN shop.yesterdayDashboard yesterday " +
            "JOIN shop.last7Dashboard last7 " +
            "JOIN shop.last30Dashboard last30 " +
            "JOIN shop.thisMonthDashboard thisMonth " +
            "JOIN shop.thisYearDashboard thisYear " +
            "JOIN shop.lastYearDashboard lastYear " +
            "JOIN shop.allTimeDashboard allTime " +
            "WHERE ?2 IS NULL OR shop.status = ?2")
    Long countVisit(String dateRange, String status);

    @Query("SELECT " +
            "shop.currencyCode as currencyCode, " +
            "shop.currencySymbol as currencySymbol, " +
            " case " +
            "   WHEN ?1 = 'TODAY' THEN sum(today.revenue) " +
            "   WHEN ?1 = 'YESTERDAY' THEN sum(yesterday.revenue) " +
            "   WHEN ?1 = 'LAST_7' THEN sum(last7.revenue) " +
            "   WHEN ?1 = 'LAST_30' THEN sum(last30.revenue) " +
            "   WHEN ?1 = 'THIS_MONTH' THEN sum(thisMonth.revenue) " +
            "   WHEN ?1 = 'THIS_YEAR' THEN sum(thisYear.revenue) " +
            "   WHEN ?1 = 'LAST_YEAR' THEN sum(lastYear.revenue) " +
            "   WHEN ?1 = 'ALL_TIME' THEN sum(allTime.revenue) " +
            "   ELSE 0 END " +
            "AS value " +
            "FROM Shop shop " +
            "JOIN shop.todayDashboard today " +
            "JOIN shop.yesterdayDashboard yesterday " +
            "JOIN shop.last7Dashboard last7 " +
            "JOIN shop.last30Dashboard last30 " +
            "JOIN shop.thisMonthDashboard thisMonth " +
            "JOIN shop.thisYearDashboard thisYear " +
            "JOIN shop.lastYearDashboard lastYear " +
            "JOIN shop.allTimeDashboard allTime " +
            "WHERE ?2 IS NULL OR shop.status = ?2 " +
            "GROUP BY shop.currencyCode, shop.currencySymbol")
    List<DashboardRevenueProjection> countRevenue(String dateRange, String status);
}
