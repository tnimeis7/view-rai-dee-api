package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Report;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Report")
public class ReportController {

    @Autowired
    private BlockService service;

    @PostMapping
    public BlockComponents saveReport(@RequestBody Report report) throws ExecutionException, InterruptedException {
        report.setReportDate();
        return service.save(report, "Report");
    }

    @GetMapping
    public List<BlockComponents> getAllReport() throws ExecutionException, InterruptedException {
        return service.getAll(Report.class, "Report");
    }

    @GetMapping("/{reportId}")
    public BlockComponents getReport(@PathVariable String reportId) throws ExecutionException, InterruptedException {
        return service.getById(reportId, Report.class, "Report");
    }

    @DeleteMapping("/{reportId}")
    public String deleteReport(@PathVariable String reportId) throws ExecutionException, InterruptedException {
        return service.delete(reportId, "Report");
    }
}
