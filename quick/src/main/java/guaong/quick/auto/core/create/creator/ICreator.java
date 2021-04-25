package guaong.quick.auto.core.create.creator;

/**
 * This is the standard for creator
 *
 * @author guaong
 * @see JavaCreator 实现类
 */
public interface ICreator {

    StringBuilder create();

    StringBuilder indentation(int num);

    StringBuilder newlines(int num);
}
