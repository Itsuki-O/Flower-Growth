/**
 * 花に渡す環境情報を保持するクラス。
 */
public class Environment {

    /**
     * 日照時間（単位：時間 [h]）
     */
    protected int sunlightHours;

    /**
     * 水分量（湿度で近似した値。単位：パーセント [%]）
     */
    protected int moisture;

    /**
     * 気温（単位：摂氏 [℃]）
     */
    protected int temperature;

    /**
     * 栄養量（季節ロジックによる目安値。単位なし）
     */
    protected int nutrition;

    /**
     * 環境情報を生成する。
     * 
     * 各フィールドは生成後に、setterを通じて値をセットする。
     * 
     */
    public Environment() {
    }

    /**
     * 日照時間を応答する。
     * 
     * @return 日照時間 [h]
     */
    public int getSunlightHours() {
        return sunlightHours;
    }

    /**
     * 日照時間を設定する。
     * 
     * @param sunlightHours 設定する日照時間 [h]
     */
    public void setSunlightHours(int sunlightHours) {
        this.sunlightHours = sunlightHours;
    }

    /**
     * 水分量を応答する。
     * 
     * @return 水分量（湿度）[%]
     */
    public int getMoisture() {
        return moisture;
    }

    /**
     * 水分量を設定する。
     * 
     * @param moisture 設定する水分量（湿度）[%]
     */
    public void setMoisture(int moisture) {
        this.moisture = moisture;
    }

    /**
     * 気温を応答する。
     * 
     * @return 気温 [℃]
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * 気温を設定する。
     * 
     * @param temperature 設定する気温 [℃]
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * 栄養量を応答する。
     * 
     * @return 栄養量
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * 栄養量を設定する。
     * 
     * @param nutrition 設定する栄養量
     */
    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }
}
