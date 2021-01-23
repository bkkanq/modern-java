import model.DisplayElement;
import model.Observer;
import model.Subject;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private int id;
    private float temperature;
    private float humidity;
    private Subject weatherData;

    // 생산자에서 Observe 할 Subject weather data를 등록함
    public CurrentConditionsDisplay(Subject weatherData, int id) {
        this.id = id;
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float tmp, float humidity, float pressure) {
        this.temperature = tmp;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("장비 ID: " + id + ", 현재 기온: " + temperature + "도, 습도: " + humidity + "%");
    }

    public static void main(String[] args) {

        WeatherData weather = new WeatherData();
        CurrentConditionsDisplay current1 = new CurrentConditionsDisplay(weather, 1);
        CurrentConditionsDisplay current2 = new CurrentConditionsDisplay(weather, 2);
        CurrentConditionsDisplay current3 = new CurrentConditionsDisplay(weather, 3);

        weather.setMeasurement(30,65, 30.4f);
        weather.setMeasurement(29,64, 30.5f);
        weather.setMeasurement(30,64, 30.6f);
    }
}
