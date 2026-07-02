/**
 * 環境情報を自ら作り出し、4種類の花のうちどれが生まれるかを決めるクラス。
 */
public class EnvironmentFlowerSelector {

    /**
     * 気温の下限 [℃]
     */
    private static final int TEMPERATURE_MIN = 3;

    /**
     * 気温の上限 [℃]
     */
    private static final int TEMPERATURE_MAX = 32;

    /**
     * 気温のしきい値（ColorChangeFlowerの判定値と同じ）[℃]
     */
    private static final int TEMPERATURE_THRESHOLD = 25;

    /**
     * 水分量の下限 [%]
     */
    private static final int MOISTURE_MIN = 53;

    /**
     * 水分量の上限 [%]
     */
    private static final int MOISTURE_MAX = 78;

    /**
     * 水分量のしきい値（FragrantFlowerの判定値と同じ）[%]
     */
    private static final int MOISTURE_THRESHOLD = 60;

    /**
     * 日照時間の下限 [h]
     */
    private static final int SUNLIGHT_MIN = 3;

    /**
     * 日照時間の上限 [h]
     */
    private static final int SUNLIGHT_MAX = 9;

    /**
     * 日照時間のしきい値（EarlyBloomFlowerの判定値と同じ）[h]
     */
    private static final int SUNLIGHT_THRESHOLD = 8;

    /**
     * 栄養量の下限
     */
    private static final int NUTRITION_MIN = 30;

    /**
     * 栄養量の上限
     */
    private static final int NUTRITION_MAX = 100;

    /**
     * 栄養量のしきい値（LongLifeFlowerの判定値と同じ）
     */
    private static final int NUTRITION_THRESHOLD = 70;

    /**
     * このセレクターが今回生成した環境情報
     */
    private final Environment environment;

    /**
     * セレクターを生成する。
     * 
     * 生成と同時に、本日の環境情報を自ら作り出す。
     * 
     */
    public EnvironmentFlowerSelector() {
        this.environment = SeasonEnvironmentProvider.createTodayEnvironment();
    }

    /**
     * 環境情報を応答する。
     * 
     * @return このセレクターが生成した、本日の環境情報
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * 環境情報から花を1つ選んで生成する。
     * 
     * 気温・水分量・日照時間・栄養量のそれぞれについて、しきい値からの
     * 差を動きうる範囲で正規化したスコアを求め、最もスコアが大きい
     * 要因に対応する花を選ぶ。
     * 
     * @return 環境情報に応じて選ばれた花（4種類のいずれか）
     */
    public Flower selectFlower() {

        double temperatureScore = normalizedMargin(
                environment.getTemperature(), TEMPERATURE_THRESHOLD, TEMPERATURE_MIN, TEMPERATURE_MAX);
        double moistureScore = normalizedMargin(
                environment.getMoisture(), MOISTURE_THRESHOLD, MOISTURE_MIN, MOISTURE_MAX);
        double sunlightScore = normalizedMargin(
                environment.getSunlightHours(), SUNLIGHT_THRESHOLD, SUNLIGHT_MIN, SUNLIGHT_MAX);
        double nutritionScore = normalizedMargin(
                environment.getNutrition(), NUTRITION_THRESHOLD, NUTRITION_MIN, NUTRITION_MAX);

        double maxScore = Math.max(Math.max(temperatureScore, moistureScore),
                                    Math.max(sunlightScore, nutritionScore));

        Flower flower;
        if (maxScore == temperatureScore) {
            flower = new ColorChangeFlower();
        } else if (maxScore == moistureScore) {
            flower = new FragrantFlower();
        } else if (maxScore == sunlightScore) {
            flower = new EarlyBloomFlower();
        } else {
            flower = new LongLifeFlower();
        }

        flower.setEnvironment(environment);
        return flower;
    }

    /**
     * しきい値からの差を、動きうる範囲で正規化する。
     * 
     * 値がしきい値以上なら「しきい値〜上限」を、しきい値未満なら
     * 「下限〜しきい値」を基準にして、-1.0〜+1.0の値を返す。
     * 
     * @param value     実際の値
     * @param threshold その要因のしきい値
     * @param min       その要因が取りうる下限
     * @param max       その要因が取りうる上限
     * @return -1.0〜+1.0に正規化した値。大きいほど、その花にとって有利。
     */
    private static double normalizedMargin(int value, int threshold, int min, int max) {
        if (value >= threshold) {
            return (double) (value - threshold) / (max - threshold);
        } else {
            return (double) (value - threshold) / (threshold - min);
        }
    }
}
