import java.util.Calendar;
import java.util.Random;

/**
 * 今の月から季節を判定し、季節ごとに決めた目安の範囲の中から
 * 気温・日照時間・湿度・栄養量をランダムに算出するクラス。
 */
public class SeasonEnvironmentProvider {

    /**
     * 季節ごとの目安範囲をまとめた内部クラス。
     */
    private static class SeasonRange {
        /**
         * 気温の下限 [℃]
         */
        final int temperatureMin;

        /**
         * 気温の上限 [℃]
         */
        final int temperatureMax;

        /**
         * 日照時間の下限 [h]
         */
        final int sunlightHoursMin;

        /**
         * 日照時間の上限 [h]
         */
        final int sunlightHoursMax;

        /**
         * 湿度の下限 [%]
         */
        final int moistureMin;

        /**
         * 湿度の上限 [%]
         */
        final int moistureMax;

        /**
         * 栄養量の下限
         */
        final int nutritionMin;

        /**
         * 栄養量の上限
         */
        final int nutritionMax;

        /**
         * 季節の目安範囲を生成する。
         * 
         * @param temperatureMin   気温の下限 [℃]
         * @param temperatureMax   気温の上限 [℃]
         * @param sunlightHoursMin 日照時間の下限 [h]
         * @param sunlightHoursMax 日照時間の上限 [h]
         * @param moistureMin      湿度の下限 [%]
         * @param moistureMax      湿度の上限 [%]
         * @param nutritionMin     栄養量の下限
         * @param nutritionMax     栄養量の上限
         */
        SeasonRange(int temperatureMin, int temperatureMax,
                    int sunlightHoursMin, int sunlightHoursMax,
                    int moistureMin, int moistureMax,
                    int nutritionMin, int nutritionMax) {
            this.temperatureMin = temperatureMin;
            this.temperatureMax = temperatureMax;
            this.sunlightHoursMin = sunlightHoursMin;
            this.sunlightHoursMax = sunlightHoursMax;
            this.moistureMin = moistureMin;
            this.moistureMax = moistureMax;
            this.nutritionMin = nutritionMin;
            this.nutritionMax = nutritionMax;
        }
    }

    /**
     * 春（3〜5月）の目安範囲。
     * 
     * 気象庁大阪観測所（2021〜2025年）の実測値に基づく。
     * 
     */
    private static final SeasonRange SPRING = new SeasonRange(9, 21, 5, 8, 55, 70, 80, 100);

    /**
     * 夏（6〜8月）の目安範囲。
     * 
     * 気象庁大阪観測所（2021〜2025年）の実測値に基づく。
     * 
     */
    private static final SeasonRange SUMMER = new SeasonRange(23, 32, 3, 9, 63, 78, 50, 70);

    /**
     * 秋（9〜11月）の目安範囲。
     * 
     * 気象庁大阪観測所（2021〜2025年）の実測値に基づく。
     * 
     */
    private static final SeasonRange AUTUMN = new SeasonRange(13, 28, 4, 7, 60, 73, 60, 80);

    /**
     * 冬（12, 1, 2月）の目安範囲。
     * 
     * 気象庁大阪観測所（2021〜2025年）の実測値に基づく。
     * 
     */
    private static final SeasonRange WINTER = new SeasonRange(3, 10, 4, 7, 53, 67, 30, 50);

    /**
     * ランダムな数値を作るための共通インスタンス
     */
    private static final Random RANDOM = new Random();

    /**
     * インスタンス化を防ぐためのプライベートコンストラクタ。全てstaticメソッドで提供する。
     */
    private SeasonEnvironmentProvider() {
    }

    /**
     * 今日の環境情報を作る。
     * 
     * 今の月に応じた季節の目安範囲の中から、ランダムに環境情報を算出する。
     * 
     * @return 算出した環境情報
     */
    public static Environment createTodayEnvironment() {

        Calendar calendar = Calendar.getInstance();

        // Calendar.MONTH は 0(1月)〜11(12月) で返ってくるため、+1 して人間が使う月番号にする
        int month = calendar.get(Calendar.MONTH) + 1;

        SeasonRange range = getSeasonRange(month);

        Environment environment = new Environment();
        environment.setTemperature(randomBetween(range.temperatureMin, range.temperatureMax));
        environment.setSunlightHours(randomBetween(range.sunlightHoursMin, range.sunlightHoursMax));
        environment.setMoisture(randomBetween(range.moistureMin, range.moistureMax));
        environment.setNutrition(randomBetween(range.nutritionMin, range.nutritionMax));

        return environment;
    }

    /**
     * 指定した月に対応する季節の目安範囲を取得する。
     * 
     * @param month 1〜12で表した月（例：4月なら4）
     * @return 対応する季節の目安範囲
     */
    private static SeasonRange getSeasonRange(int month) {
        if (isSpring(month)) {
            return SPRING;
        } else if (isSummer(month)) {
            return SUMMER;
        } else if (isAutumn(month)) {
            return AUTUMN;
        } else {
            // 春・夏・秋のいずれでもなければ冬（12月, 1月, 2月）
            return WINTER;
        }
    }

    /**
     * 指定した月が春（3〜5月）かどうかを判定する。
     * 
     * @param month 1〜12で表した月
     * @return 春であればtrue
     */
    private static boolean isSpring(int month) {
        return month >= 3 && month <= 5;
    }

    /**
     * 指定した月が夏（6〜8月）かどうかを判定する。
     * 
     * @param month 1〜12で表した月
     * @return 夏であればtrue
     */
    private static boolean isSummer(int month) {
        return month >= 6 && month <= 8;
    }

    /**
     * 指定した月が秋（9〜11月）かどうかを判定する。
     * 
     * @param month 1〜12で表した月
     * @return 秋であればtrue
     */
    private static boolean isAutumn(int month) {
        return month >= 9 && month <= 11;
    }

    /**
     * min以上max以下の範囲で、ランダムな整数を1つ返す。
     * 
     * @param min 範囲の下限（この値を含む）
     * @param max 範囲の上限（この値を含む）
     * @return ランダムに選ばれた整数
     */
    private static int randomBetween(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
    }
}
