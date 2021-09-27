package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.Report;
import th.ac.ku.viewbackend.service.ArticleService;
import th.ac.ku.viewbackend.service.ReportService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping
    public String saveReport(@RequestBody Report report) throws ExecutionException, InterruptedException {
        return reportService.saveReport(report);
    }

    @GetMapping
    public List<Report> getAllReport() throws ExecutionException, InterruptedException {
        return reportService.getAllReport();
    }

    @GetMapping("{reportId}")
    public Report getReport(@RequestBody String reportId) throws ExecutionException, InterruptedException {
        return reportService.getReport(reportId);
    }

    @PutMapping
    public String updateReport(@RequestBody Report report) throws ExecutionException, InterruptedException {
        return reportService.updateReport(report);
    }

    @DeleteMapping
    public String deleteReport(@RequestBody String reportId) throws ExecutionException, InterruptedException {
        return reportService.deleteReport(reportId);
    }
}
