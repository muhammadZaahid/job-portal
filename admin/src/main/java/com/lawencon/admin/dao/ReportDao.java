package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.pojo.report.ApplicantReportPojo;
import com.lawencon.base.ConnHandler;

@Repository
public class ReportDao {

    @SuppressWarnings("unchecked")
    public List<ApplicantReportPojo> getForApplicant(String startDate, String endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tc.name, tjo.title, ta.current_stage, ");
        sb.append("CASE ");
        sb.append("WHEN ta.current_stage = 'hired' ");
        sb.append("THEN CAST(EXTRACT(days from(te.created_at - ta.created_at)) as INTEGER) ");
        sb.append("ELSE CAST(EXTRACT(days from(CURRENT_DATE - ta.created_at)) AS INTEGER) END as applicationDuration ");
        sb.append("FROM t_applicant ta ");
        sb.append("JOIN t_candidate tc on ta.candidate_id = tc.id ");
        sb.append("JOIN t_job_vacancy tjo on ta.job_vacancy_id = tjo.id ");
        sb.append("LEFT JOIN t_employee te on te.candidate_id = tc.id ");

        if (startDate != null && !startDate.isEmpty()) {
            sb.append("WHERE cast(ta.created_at as date) > cast(:startDate as date) ");
            if (endDate != null && !endDate.isEmpty()) {
                sb.append("AND cast(ta.created_at as date) < cast(:endDate as date) ");
            }
        }

        sb.append("ORDER BY ta.created_at");
        Query query = ConnHandler.getManager().createNativeQuery(sb.toString());
        if (startDate != null && !startDate.isEmpty()) {
            query.setParameter("startDate", startDate);
            if (endDate != null && !endDate.isEmpty()) {
                query.setParameter("endDate", endDate);
            }

        }

        List<Object[]> objects = query.getResultList();

        List<ApplicantReportPojo> result = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            ApplicantReportPojo resp = new ApplicantReportPojo();
            resp.setCandidateName((String) objects.get(i)[0]);
            resp.setJobTitle((String) objects.get(i)[1]);
            resp.setCurrentStage((String) objects.get(i)[2]);
            Integer totalDays = (Integer) objects.get(i)[3];
            if (totalDays == 1) {
                resp.setTotalDays(totalDays + " day");
            } else {
                resp.setTotalDays(totalDays + " days");
            }
            resp.setDaysNumber((totalDays));
            result.add(resp);
        }

        return result;
    }
}
