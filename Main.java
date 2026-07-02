import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" 花の遺伝子シミュレーション");
        System.out.println("====================================\n");

        // ─── 1. 環境から生まれた花を先に表示 ─────────────────────────
        // 環境情報の生成〜花の選定は EnvironmentFlowerSelector が自分で行う。
        // main はここで Environment を作らず、セレクターにgetterで問い合わせるだけ。
        System.out.println("今の季節（月）に応じて、本日の環境情報を生成しています...");
        EnvironmentFlowerSelector selector = new EnvironmentFlowerSelector();
        Environment environment = selector.getEnvironment();

        System.out.println();
        System.out.println("【本日の環境情報（大阪・季節ロジック）】");
        System.out.println("  気温       : " + environment.getTemperature()   + " ℃");
        System.out.println("  日照時間   : " + environment.getSunlightHours() + " h");
        System.out.println("  水分量(湿度): " + environment.getMoisture()      + " %");
        System.out.println("  栄養量     : " + environment.getNutrition());
        System.out.println();

        Flower environmentFlower = selector.selectFlower();

        System.out.println("【本日の環境から生まれた花】");
        System.out.println("┌──────────────────────────────────────┐");
        System.out.println("  " + environmentFlower.getName() + "（" + environmentFlower.getGeneCode() + "）");
        System.out.println("└──────────────────────────────────────┘");

        environmentFlower.perform();

        System.out.println();

        // ─── 2. 花のリストを作成 ─────────────────────────────────────
        List<Flower> flowers = new ArrayList<>();
        flowers.add(new EarlyBloomFlower());
        flowers.add(new ColorChangeFlower());
        flowers.add(new FragrantFlower());
        flowers.add(new LongLifeFlower());

        // ─── 3. 各花のシミュレーションを実行 ────────────────────────
        for (Flower flower : flowers) {

            // 花に環境情報をセット（setterを使用）
            flower.setEnvironment(environment);

            System.out.println("┌──────────────────────────────────────┐");
            System.out.println("  " + flower.getName() + "（" + flower.getGeneCode() + "）");
            System.out.println("└──────────────────────────────────────┘");

            flower.perform();

            System.out.println();
        }

        System.out.println("------------------------------------");
        System.out.println("シミュレーションが終了しました。");
    }
}
