package net.kosmo.fixbookgui.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PageTurnWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BookScreen.class)
public abstract class MixinBookScreen extends Screen {
    @Shadow
    @Final
    public static Identifier BOOK_TEXTURE;
    @Shadow
    private int cachedPageIndex;
    @Shadow
    private BookScreen.Contents contents;
    @Shadow
    private int pageIndex;

    @Shadow
    private List<OrderedText> cachedPage;
    @Shadow
    private Text pageIndexText;

    @Shadow
    private PageTurnWidget nextPageButton;
    @Shadow
    private PageTurnWidget previousPageButton;

    @Shadow
    @Final
    private boolean pageTurnSound;

    protected MixinBookScreen() {
        super(NarratorManager.EMPTY);
    }

    @Shadow
    protected abstract int getPageCount();

    @Shadow
    public abstract Style getTextStyleAt(double x, double y);

    @Shadow
    protected abstract void goToNextPage();

    @Shadow
    protected abstract void goToPreviousPage();

    @Shadow
    protected abstract void updatePageButtons();


    /**
     * @author mworzala (Implementation: KosmoMoustache)
     * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
     * @see <a href="https://gist.github.com/mworzala/9a8d86803784c9c81aac77d9a7f9fb2b">Gist</a>
     */
    @Overwrite
    public void addCloseButton() {
//        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.client.setScreen(null)).dimensions(this.width / 2 - 100, 196, 200, 20).build());
        int y = (this.height - 192) / 2;
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.client.setScreen(null)).dimensions(this.width / 2 - 100, y + 196, 200, 20).build());

    }


    /**
     * @author mworzala (Implementation: KosmoMoustache)
     * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
     * @see <a href="https://gist.github.com/mworzala/9a8d86803784c9c81aac77d9a7f9fb2b">Gist</a>
     */
    @Overwrite
    public void addPageButtons() {
        int i = (this.width - 192) / 2;
//        int j = 2;
        int y = (this.height - 192) / 2;
        this.nextPageButton = this.addDrawableChild(new PageTurnWidget(i + 116, y + 159, true, button -> this.goToNextPage(), this.pageTurnSound));
        this.previousPageButton = this.addDrawableChild(new PageTurnWidget(i + 43, y + 159, false, button -> this.goToPreviousPage(), this.pageTurnSound));
//        this.nextPageButton = this.addDrawableChild(new PageTurnWidget(i + 116, 159, true, button -> this.goToNextPage(), this.pageTurnSound));
//        this.previousPageButton = this.addDrawableChild(new PageTurnWidget(i + 43, 159, false, button -> this.goToPreviousPage(), this.pageTurnSound));
        this.updatePageButtons();
    }

    /**
     * @author mworzala (Implementation: KosmoMoustache)
     * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
     * @see <a href="https://gist.github.com/mworzala/9a8d86803784c9c81aac77d9a7f9fb2b">Gist</a>
     */
    @Overwrite
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BOOK_TEXTURE);
        int i = (this.width - 192) / 2;
        // Start bug fix
//        int j = 2;
//        this.drawTexture(matrices, i, 2, 0, 0, 192, 192);
        int y = (this.height - 192) / 2;
        this.drawTexture(matrices, i, y + 2, 0, 0, 192, 192);
        //  End bug fix
        if (this.cachedPageIndex != this.pageIndex) {
            StringVisitable stringVisitable = this.contents.getPage(this.pageIndex);
            this.cachedPage = this.textRenderer.wrapLines(stringVisitable, 114);
            this.pageIndexText = Text.translatable("book.pageIndicator", this.pageIndex + 1, Math.max(this.getPageCount(), 1));
        }
        this.cachedPageIndex = this.pageIndex;
        int k = this.textRenderer.getWidth(this.pageIndexText);
        // Start bug fix
//        this.textRenderer.draw(matrices, this.pageIndexText, (float) (i - k + 192 - 44), 18.0f, 0);
        this.textRenderer.draw(matrices, this.pageIndexText, (float) (i - k + 192 - 44), y + 18.0F, 0);
        // End bug fix
        int l = Math.min(128 / this.textRenderer.fontHeight, this.cachedPage.size());
        for (int m = 0; m < l; ++m) {
            OrderedText orderedText = this.cachedPage.get(m);
            // Start bug fix
//            this.textRenderer.draw(matrices, orderedText, (float) (i + 36), (float)  (32 + m * this.textRenderer.fontHeight), 0);
            this.textRenderer.draw(matrices, orderedText, (float) (i + 36), (float) y + (32 + m * this.textRenderer.fontHeight), 0);
            // End bug fix
        }
        Style style = this.getTextStyleAt(mouseX, mouseY);
        if (style != null) {
            this.renderTextHoverEffect(matrices, style, mouseX, mouseY);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }
}
