package train.a2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import train.a2.service.AsyncService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
@RequestMapping("/async")
public class AsyncController {
    @Autowired
    AsyncService asyncService = null;
    private Future<List<Long>> numberListTask;

    @RequestMapping("/home")
    public ModelAndView homepage(ModelAndView mv){
        mv.setViewName("async");
        return mv;
    }

    @RequestMapping(value = "/startJob")
    public ResponseEntity<String> startTask() {
        numberListTask = asyncService.generateReport();
        return ResponseEntity.status(200).body("job started");
    }

    @RequestMapping(value = "/stopJob")
    public ResponseEntity<String> cancel() {
        numberListTask.cancel(true);
        return ResponseEntity.status(200).body("Stopped: ".concat(String.valueOf(numberListTask.isCancelled())));
    }

    @RequestMapping(value = "/jobStatus")
    public ResponseEntity<String> jobStatus() {
        String status = null;
        Integer done = -1;
        try {
            if(numberListTask.isCancelled()) {
                done = asyncService.doneSoFar();
                status = "Cancelled. " + done + " out of " + 250000000000l;
            } else if (numberListTask.isDone()) {
                done = numberListTask.get().size();
                status = "Done 100%. Size: " + done;
            } else {
                done = asyncService.doneSoFar();
                status = "Processed: " + done + " of 250000000000";
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(200).body(status);
    }

}
