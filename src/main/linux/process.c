struct task_struct {

    long state; // 进程状态

    struct mm_struct *mm; // 虚拟内存结构体

    pid_t pid; // 进程号

    struct task_struct __rcu *parent; // 指向父进程的指针

    struct list_head children; // 子进程列表

    struct fs_struct *fs; // 存放文件系统信息的指针

		// 每个进程被创建时，files的前三位被填入默认值，分别指向标准输入流、标准输出流、标准错误流。
		// 文件描述符就是指这个文件指针数组的索引，所以程序的文件描述符默认情况下 0 是输入，1 是输出，2 是错误。
    struct files_struct *files; // 一个数组，包含该进程打开的文件指针
};
