package ua.meta.sarna;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

    @WebServlet(name = "Questionary", urlPatterns = "/questionary")
    public class QuestionServlet extends HttpServlet {
        private static final String ANSW = "<html>" +
                "<head><title>Questionary Results</title></head>" +
                "<body><h3>Questionary Results</h3>" +
                "<p>Total number of questionarys taken: %d<br><br>" +
                "Car owners: %s<br><br>" +
                "Cars by Type: <br>" +
                "Sedans: %s, Jeeps: %s, Hatchbacks: %s, Other: %s<br><br>" +
                "Car Uses: <br>" +
                "For work: %s, For leisure: %s, For traveling: %s, For Sport: %s</p></body></html>";
        private ArrayList<Person> answers = new ArrayList<>();

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            addAnswer(readAnswer(req));
            writeResponse(resp);
        }

        private synchronized void addAnswer(Person answer) {
            if (answer != null) {
                answers.add(answer);
            }
        }


        private void writeResponse(HttpServletResponse resp) throws IOException {
            int n = answers.size();
            Map<String, Integer> resMap = generateResultMap();
            resp.getWriter().printf(ANSW, n,
                    getFromMap("carOwners", resMap, n), getFromMap("sedanOwners", resMap, n),
                    getFromMap("jeepOwners", resMap, n), getFromMap("sportsOwners", resMap, n),
                    getFromMap("otherOwners", resMap, n), getFromMap("carUse0", resMap, n),
                    getFromMap("carUse1", resMap, n), getFromMap("carUse2", resMap, n),
                    getFromMap("carUse3", resMap, n));

        }

        private String getFromMap(String key, Map<String, Integer> map, int total) {
            String str = String.format(map.getOrDefault(key, 0) + " ( %.1f%% )", map.getOrDefault(key, 0) * 100.0 / total);
            return str;
        }

        private Map<String, Integer> generateResultMap() {
            Map<String, Integer> resultsMap = new HashMap<>();
            countCarOwners(resultsMap);
            countCarOwnersByType(resultsMap);
            countCarUses(resultsMap);
            return resultsMap;
        }

        private void countCarOwners(Map<String, Integer> resultsMap) {
            for (Person answer : answers) {
                if (answer.isHasCar()) {
                    resultsMap.compute("carOwners", (k, v) -> v == null ? 1 : (resultsMap.get(k) + 1));
                }
            }
        }

        private void countCarOwnersByType(Map<String, Integer> resultsMap) {
            for (Person answer : answers) {
                switch (answer.getCarType()) {
                    case SEDAN: {
                        resultsMap.compute("sedanOwners", (k, v) -> v == null ? 1 : (resultsMap.get(k) + 1));
                        break;
                    }
                    case JEEP: {
                        resultsMap.compute("jeepOwners", (k, v) -> v == null ? 1 : (resultsMap.get(k) + 1));
                        break;
                    }
                    case HATCHBACK: {
                        resultsMap.compute("hatchbackOwners", (k, v) -> v == null ? 1 : (resultsMap.get(k) + 1));
                        break;
                    }
                    case OTHER: {
                        resultsMap.compute("otherOwners", (k, v) -> v == null ? 1 : (resultsMap.get(k) + 1));
                        break;
                    }
                }
            }
        }

        private void countCarUses(Map<String, Integer> resultsMap) {
            for (Person answer : answers) {
                for (int i = 0; i < answer.getCarUse().length; i++) {
                    if (answer.getCarUse()[i]) {
                        resultsMap.compute("carUse" + i, (k, v) -> v == null ? 1 : (resultsMap.get(k) + 1));
                    }
                }
            }
        }

        private Person readAnswer(HttpServletRequest req) {
            try {
                String name = req.getParameter("name");
                String lastname = req.getParameter("lastname");
                int age = Integer.parseInt(req.getParameter("age"));
                boolean hasCar = Boolean.valueOf(req.getParameter("hasCar"));
                CarType carType = CarType.valueOf(req.getParameter("carType"));
                int carUseNumber = Integer.parseInt(req.getParameter("carUseNumber"));
                boolean[] carUse = new boolean[carUseNumber];
                for (int i = 0; i < carUseNumber; i++) {
                    carUse[i] = Boolean.valueOf(req.getParameter("carUse" + i));
                }
                return new Person(name, lastname, age, hasCar, carType, carUse);
            } catch (Exception e) {
                return null;
            }
        }
}
