package org.example.lazy_calculate.model;

import javafx.beans.property.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class LazinessModel {
    private final IntegerProperty plannedTasks = new SimpleIntegerProperty(0);
    private final IntegerProperty completedTasks = new SimpleIntegerProperty(0);

    private final DoubleProperty lazinessCoefficient = new SimpleDoubleProperty(0.0);
    private final StringProperty procrastinationLevel = new SimpleStringProperty("Неизвестно");
    private final StringProperty phrase = new SimpleStringProperty("Введите данные для анализа");

    private static final Random RANDOM = new Random();

    private static final Map<String, List<String>> QUOTES = Map.of(
            "NO_PLANS", List.of(
                    "Если ничего не планировать, то разочаровать себя невозможно. Гениально!",
                    "План на день: ничего не делать. Выполнено на 100%!",
                    "Лежать в направлении мечты — тоже стратегия. Но планов-то нет!",
                    "Дзен постигнут. Отсутствие планов — высшая форма свободы.",
                    "День сурка отменяется, сегодня день кота. Планов ноль."
            ),
            "PERFECT", List.of(
                    "Вы точно человек? Капчу в интернете проходите с первого раза?",
                    "Илон Маск нервно курит, глядя на вашу продуктивность.",
                    "Машина! Терминатор! Киборг, помноженный на вечность!",
                    "Сделано всё! Теперь можно с чистой совестью посмотреть в стену.",
                    "Ваш уровень энергии пугает. Поделитесь батарейкой?"
            ),
            "LOW", List.of(
                    "Почти идеально. Оставили пару дел, чтобы завтра не было скучно?",
                    "Отличный результат! А эти недоделки мы спишем на творческую паузу.",
                    "Вы молодец, но диван всё равно смотрел на вас с укором.",
                    "Оценка: 9/10. Один балл снят за то, что вы не робот.",
                    "Продуктивность зашкаливает, но на чай с печенькой время всё же нашлось."
            ),
            "MEDIUM", List.of(
                    "Баланс соблюден: половину сделали, половину прокрастинировали. Танос одобряет.",
                    "Делать дела — это мейнстрим. Вы предпочитаете «настаивать» их до завтра.",
                    "Работа не волк, в лес не убежала... а жаль, правда?",
                    "Золотая середина: и не перетрудились, и перед совестью вроде как чисто.",
                    "Вы уверенно двигаетесь к цели... со скоростью уставшей улитки."
            ),
            "CRITICAL", List.of(
                    "Удивительно, как вы вообще нашли силы нажать кнопку в этой программе.",
                    "Ваше тотемное животное сегодня — ленивец в коме.",
                    "Если бы за прокрастинацию давали Оскар, вы бы поленились за ним идти.",
                    "Планы на день были грандиозными, но диван оказался более убедительным.",
                    "Вы не ленивы, вы просто находитесь в режиме строжайшего энергосбережения."
            )
    );

    public void updateData(int planned, int completed) {
        this.plannedTasks.set(planned);
        this.completedTasks.set(completed);
        calculate();
    }

    private void calculate() {
        int p = plannedTasks.get();
        int c = completedTasks.get();

        if (p <= 0) {
            lazinessCoefficient.set(0);
            procrastinationLevel.set("Отсутствует");
            phrase.set(getRandomQuote("NO_PLANS"));
            return;
        }

        double coeff = Math.max(0.0, 1.0 - ((double) c / p)) * 100.0;
        lazinessCoefficient.set(coeff);

        if (coeff == 0) {
            procrastinationLevel.set("Отсутствует");
            phrase.set(getRandomQuote("PERFECT"));
        } else if (coeff < 30) {
            procrastinationLevel.set("Низкий");
            phrase.set(getRandomQuote("LOW"));
        } else if (coeff < 70) {
            procrastinationLevel.set("Средний");
            phrase.set(getRandomQuote("MEDIUM"));
        } else {
            procrastinationLevel.set("Критический");
            phrase.set(getRandomQuote("CRITICAL"));
        }
    }

    private String getRandomQuote(String category) {
        List<String> quotesList = QUOTES.get(category);
        return quotesList.get(RANDOM.nextInt(quotesList.size()));
    }

    public IntegerProperty plannedTasksProperty() { return plannedTasks; }
    public IntegerProperty completedTasksProperty() { return completedTasks; }
    public DoubleProperty lazinessCoefficientProperty() { return lazinessCoefficient; }
    public StringProperty procrastinationLevelProperty() { return procrastinationLevel; }
    public StringProperty phraseProperty() { return phrase; }
}