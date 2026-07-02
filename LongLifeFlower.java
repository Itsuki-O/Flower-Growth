/**
 * 遺伝子C：通常より長く咲き続ける花。
 */
public class LongLifeFlower extends Flower {

    /**
     * 長寿の花を生成する。
     */
    public LongLifeFlower() {
        this.name = "長寿の花";
        this.geneCode = "遺伝子C";
    }

    @Override
    public void futureBehavior() {
        extendBloom(); // ① 遺伝子による基本行動

        // ② 環境要因（栄養量）による度合いの調整
        if (environment.getNutrition() >= 70) {
            System.out.println("栄養量が十分（" + environment.getNutrition() + "） → 寿命がさらに延びます");
        } else {
            System.out.println("栄養量が不足（" + environment.getNutrition() + "） → 通常程度の長さに短縮されます");
        }
    }

    /**
     * 通常より長く咲き続ける（このクラス独自のメソッド。親クラスには存在しない）。
     */
    public void extendBloom() {
        System.out.println("通常より長く咲き続けます");
    }
}
