/**
 * 遺伝子B：咲いた後、時間経過で花の色が変わる花。
 */
public class ColorChangeFlower extends Flower {

    /**
     * 色変わりの花を生成する。
     */
    public ColorChangeFlower() {
        this.name     = "色変わりの花";
        this.geneCode = "遺伝子B";
    }

    @Override
    public void futureBehavior() {
        changeColor(); // ① 遺伝子による基本行動

        // ② 環境要因（気温）による度合いの調整
        if (environment.getTemperature() >= 25) {
            System.out.println("気温が高い（" + environment.getTemperature() + "℃） → 色の変化が速く、鮮やかに変化します");
        } else {
            System.out.println("気温が低い（" + environment.getTemperature() + "℃） → 色の変化が遅く、淡く変化します");
        }
    }

    /**
     * 花の色を変化させる（このクラス独自のメソッド。親クラスには存在しない）。
     */
    public void changeColor() {
        System.out.println("花の色が変化します");
    }
}
